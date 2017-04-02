package visitor;

import abstractSyntax.FormalParameter.FormalPar;

import java.util.List;

public class utils {

    static String ppStringList (List<String> list, boolean parentheses) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (Object item : list.subList(0, list.size())) sb.append(", " + item).reverse();
        if (parentheses) {sb.insert(0, '('); sb.append(')');}
        return sb.toString();
    }

    static String ppLabelsList(List<Integer> list) {
        StringBuffer sb = new StringBuffer();
        sb.append(list.get(0));
        for (Integer uNum : list.subList(1, list.size())) sb.append(", "+uNum);
        return sb.toString();
    }

}
