package semanticAnalyzer.symbol;

import java.util.*;
import semanticAnalyzer.symbol.Exceptions.*;

class Tuple<B, L> {
	public final B binding;
	public final L level;
	public Tuple(B binding, L level) {
		this.binding = binding;
		this.level = level;
	}
	public B getBinding() {
		return binding;
	}
	public L getLevel() {
		return level;
	}
}

public class SymbolTable<T> {
	HashMap<String, LinkedList<Tuple<T, Integer>>> uniqueMap = new HashMap<>();
	Stack<Stack<String>> allchanges = new Stack<>();
	Stack<String> changesInThisScope = new Stack<>();
	private int currentLevel = 0, times = 0;

	private void levelup() {
		currentLevel++;
	}
	private void leveldown() {
		currentLevel--;
	}

	public void beginScope() {
		levelup();
		if (currentLevel > 1) {
			allchanges.push(changesInThisScope);
			changesInThisScope.removeAllElements();
		}
	}

	public void endScope()  throws InvalidLevelException {
		allchanges.push(changesInThisScope);
		if (currentLevel > 0) {
			leveldown();
			Stack<String> changes = allchanges.pop();
			changes.forEach(id -> {
				LinkedList<Tuple<T, Integer>> itens = uniqueMap.get(id);
				if (itens != null  && itens.size() > 1) {
					if (itens.getLast().getLevel() == currentLevel + 1) {
						itens.removeLast();
						uniqueMap.put(id, itens);
					} else System.out.println("EITA LÊLÊ");
				} else uniqueMap.remove(id);
			});
		} else throw new InvalidLevelException(times++);
	}

	//returns null if there is no a binding for id
	public T get(String id) {
		return (uniqueMap.containsKey(id))? uniqueMap.get(id).getLast().getBinding() : null;
	}

	public void put(String id, T bnd) throws AlreadyBoundException {
		if (uniqueMap.containsKey(id)) {
			if (uniqueMap.get(id).getLast().getLevel() == currentLevel)
				throw new AlreadyBoundException();
			else {
				LinkedList<Tuple<T, Integer>> updated_item = uniqueMap.get(id);
				updated_item.addLast(new Tuple<>(bnd, currentLevel));
				uniqueMap.put(id, updated_item);
				if (currentLevel > 0) changesInThisScope.add(id);
			}
		} else {
			LinkedList<Tuple<T, Integer>> updated_item = new LinkedList<>();
			updated_item.addLast(new Tuple<>(bnd, currentLevel));
			uniqueMap.put(id, updated_item);
			if (currentLevel > 0) changesInThisScope.add(id);
		}
	}

	//Não foi tratado o caso de choque de Bindings diferentes na mesma posição do hashmap
}

