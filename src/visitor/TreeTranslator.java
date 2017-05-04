package visitor;

import IntermediateRepresentation.Binding.*;
import IntermediateRepresentation.PrettyPrint;
import IntermediateRepresentation.Temp.*;
import IntermediateRepresentation.Tree.*;
import abstractSyntax.ConformantArray.*;
import abstractSyntax.Constant.*;
import abstractSyntax.Exp.*;
import abstractSyntax.FormalParameter.*;
import abstractSyntax.Node.*;
import abstractSyntax.Operator.*;
import abstractSyntax.Stm.*;
import abstractSyntax.labelsAndTypes.*;
import abstractSyntax.procedureAndFunctionDeclaration.*;
import abstractSyntax.variablesDeclaration.*;
import utils.symbol.*;
import utils.symbol.Exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// print frames assistent
class frameTuple<B, L> {
    public final B stm;
    public final L label;
    public final int size;
    public frameTuple(B stm, L label, int size) {
        this.stm = stm;
        this.label = label;
        this.size = size;
    }
    public B getSubp() {
        return stm;
    }
    public L getLabel() {
        return label;
    }
    public int getSize() { return size; }
}

//Each function and procedure is translated into a SubprogramSegment, thus
//the visit to a Program returns the correspondent List<SubprogramSegment>
//For the sake of simplicity, we assume that the main program has a frame
//which hosts the global variables.

public class TreeTranslator implements PascalVisitor {

    int currentLevel;
    Frame currentFrame;
    Temp functionReturn, framePointer; // default reg to functionReturn and framePointer

    Stack<Frame> framesStack;
    LinkedList<frameTuple<Stm, LABEL>> subprogs;

    SymbolTable<Binding> env;
    IntermediateRepresentation.PrettyPrint prettyPrint;

    public TreeTranslator (Program prog) {
        framesStack = new Stack<>();
        subprogs = new LinkedList<>();
        env = new SymbolTable<>();

        functionReturn = new Temp();
        framePointer = new Temp();

        try {
            prettyPrint = new PrettyPrint(new PrintStream(new File(System.getProperty("user.dir") + "/program.tree")));

            prettyPrint.prStm((SEQ) VisitProgram(prog));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName());
        }
        // print just subprograms: labels, frame size, and stms
        prettyPrint = new PrettyPrint(System.out);

        subprogs.descendingIterator().forEachRemaining( f -> {
            prettyPrint.prStm(f.getLabel());
            System.out.println("FrameSize: " + f.size);
            prettyPrint.prStm(f.getSubp());
            System.out.println();
        });

    }

    private Stm StmListToSEQ (List<?> stms) {
        Stm seq = null;
        seq = (Stm) stms.get(stms.size() - 1);
        if (stms.size() > 2)
            for (Object stm : stms.subList(0, stms.size() - 2))
                seq = new SEQ((Stm) stm, seq);
        return seq;
    }

    @Override
    public Object VisitConformantArrayParameter(ConformantArrayParameter conformantArrayParameter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object VisitMultiDimensionConformant(MultiDimensionConformant multiDimensionConformant) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object VisitOneDimensionConformant(OneDimensionConformant oneDimensionConformant) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object VisitConstantId(IdConstant idConstant) {
        return ((Cons) env.get(idConstant.id)).ord;
    }

    @Override
    public Object VisitBooleanConstant(BooleanConstant booleanConstant) {
        return booleanConstant.value? 1 : 0;
    }

    @Override
    public Object VisitCharacterConstant(CharacterConstant characterConstant) {
        return (int) characterConstant.value;
    }

    @Override
    public Object VisitSignedNumber(SignedNumber signedNumber) {
        return signedNumber.sign == Sign.PLUS?
                signedNumber.unsNum.value :
                    -signedNumber.unsNum.value;
    }

    @Override
    public Object VisitUnsignedNumber(UnsignedNumber unsignedNumber) {
        return unsignedNumber.value;
    }

    @Override
    public Object VisitBinaryArithmeticExpression(BinaryArithmeticExpression binaryArithmeticExpression) {
        //TODO
        Exp lf = (Exp) binaryArithmeticExpression.left.accept(this);
        Exp rt = (Exp) binaryArithmeticExpression.right.accept(this);

        if (binaryArithmeticExpression.op == BinaryArithmeticOperator.PLUS)
            return new Ex(new BINOP(BINOP.PLUS, lf.unEx(), rt.unEx()));
        else if (binaryArithmeticExpression.op == BinaryArithmeticOperator.MINUS)
            return new Ex(new BINOP(BINOP.MINUS, lf.unEx(), rt.unEx()));
        else if (binaryArithmeticExpression.op == BinaryArithmeticOperator.TIMES)
            return new Ex(new BINOP(BINOP.MUL, lf.unEx(), rt.unEx()));
        else if (binaryArithmeticExpression.op == BinaryArithmeticOperator.DIV)
            return new Ex(new BINOP(BINOP.DIV, lf.unEx(), rt.unEx()));
        else {
            List<Expr> exprs = new ArrayList<Expr>();
            exprs.add(lf.unEx()); exprs.add(rt.unEx());
            return new Ex(new CALL(new NAME(new Label("$CalcMOD_0")), exprs));
        }
    }

    @Override
    public Object VisitBinaryBooleanExpression(BinaryBooleanExpression binaryBooleanExpression) {
        Exp lf = (Exp) binaryBooleanExpression.left.accept(this);
        Exp rt = (Exp) binaryBooleanExpression.right.accept(this);
        if (binaryBooleanExpression.op == BinaryBooleanOperator.AND) {
            return new Cx() {
                public Stm unCx(Label t, Label f) {
                    Label t1 = new Label();
                    return new SEQ(lf.unCx(t1, f),
                            new SEQ(new LABEL(t1),
                                    rt.unCx(t, f) ) );
                }
            };
        } else { // op == OR
            return new Cx() {
                public Stm unCx (Label t, Label f) {
                    Label f1 = new Label();
                    return new SEQ(lf.unCx(t, f1),
                            new SEQ(new LABEL(f1),
                                    rt.unCx(t, f)));
                }
            };
        }
    }

    @Override
    public Object VisitCharLiteral(CharLiteral charLiteral) {
        return new Ex(new CONST((int) charLiteral.value));
    }

    @Override
    public Object VisitBooleanLiteral(BooleanLiteral booleanLiteral) {
        return booleanLiteral.value? new Ex(new CONST(1)) : new Ex(new CONST(0));
    }

    @Override
    public Object VisitCharacterLiteral(CharacterLiteral characterLiteral) {
        return new Ex(new CONST((int) characterLiteral.value));
    }

    @Override
    public Object VisitIndexedVariable(IndexedVariable indexedVariable) {
        // TODO
        return null;
    }

    @Override
    public Object VisitNotExpression(NotExpression notExpression) {
        Exp eb = (Exp) notExpression.exp.accept(this);
        return new Cx() {
            public Stm unCx(Label t, Label f) {
                return eb.unCx(f, t);
            }
        };
    }

    @Override
    public Object VisitNumberLiteral(NumberLiteral numberLiteral) {
        return new Ex(new CONST(numberLiteral.value));
    }

    @Override
    public Object VisitRelationalExpression(RelationalExpression relationalExpression) {
        Exp left = (Exp) relationalExpression.left.accept(this),
            right = (Exp) relationalExpression.right.accept(this);
        switch (relationalExpression.op) {
            case EQ:
                return new Cx() {
                    @Override
                    public Stm unCx(Label t, Label f) {
                        return new CJUMP(CJUMP.EQ, left.unEx(), right.unEx(), t, f);
                    }
                };
            case NEQ:
                return new Cx() {
                    @Override
                    public Stm unCx(Label t, Label f) {
                        return new CJUMP(CJUMP.NE, left.unEx(), right.unEx(), t, f);
                    }
                };
            case LT:
                return new Cx() {
                    @Override
                    public Stm unCx(Label t, Label f) {
                        return new CJUMP(CJUMP.LT, left.unEx(), right.unEx(), t, f);
                    }
                };
            case GT:
                return new Cx() {
                    @Override
                    public Stm unCx(Label t, Label f) {
                        return new CJUMP(CJUMP.GT, left.unEx(), right.unEx(), t, f);
                    }
                };
            case LTE:
                return new Cx() {
                    @Override
                    public Stm unCx(Label t, Label f) {
                        return new CJUMP(CJUMP.LE, left.unEx(), right.unEx(), t, f);
                    }
                };
            case GTE:
                return new Cx() {
                    @Override
                    public Stm unCx(Label t, Label f) {
                        return new CJUMP(CJUMP.GE, left.unEx(), right.unEx(), t, f);
                    }
                };
            default: return null;
        }
    }

    @Override
    public Object VisitSignedExpression(SignedExpression signedExpression) {
        /*return (signedExpression.sign == Sign.PLUS)? signedExpression.exp.accept(this) :
                            new BINOP(BINOP.MINUS, new CONST(0), ((Ex) signedExpression.exp.accept(this)).unEx());*/
        return signedExpression.exp.accept(this);
    }

    @Override
    public Object VisitFormalPar(FormalPar formalPar) {
        Rep rep = (Rep) formalPar.type.accept(this);
        int loc = currentFrame.alloc(rep.size());
        try {
            env.put(formalPar.name, new Var(loc, rep, currentLevel));
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object VisitArray(Array array) {
        OrdinalRep rangeRep = (OrdinalRep) array.range.accept(this);
        Rep elemRep = (Rep) array.elemTy.accept(this);
        return new ArrayRep(rangeRep.lower, rangeRep.upper, elemRep);
    }

    @Override
    public Object VisitEnumeratedType(EnumeratedType enumeratedType) {
        int i=0;
        for (String nm : enumeratedType.newConstants)
            try {
                env.put(nm, new Cons(i++));
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            }
        return new OrdinalRep(0, i);
    }

    @Override
    public Object VisitPrimitiveType(PrimitiveType primitiveType) {
        if (primitiveType == PrimitiveType.INTEGER )
            return OrdinalRep.INTEGER_REP;
        else if (primitiveType == PrimitiveType.CHAR )
            return OrdinalRep.CHAR_REP;
        else
            return OrdinalRep.BOOLEAN_REP;
    }

    @Override
    public Object VisitSubRangeType(SubrangeType subrangeType) {
        int low = (Integer) subrangeType.low.accept(this);
        int high = (Integer) subrangeType.high.accept(this);
        return new OrdinalRep(low, high);
    }

    @Override
    public Object VisitTypeDefinition(TypeDefinition typeDefinition) {
        Rep rep= (Rep) typeDefinition.ty.accept(this);
        try {
            env.put(typeDefinition.id, rep);
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object VisitTypeId(IdType idType) {
        return env.get(idType.id);
    }

    @Override
    public Object VisitBlock(Block block) {
        List<SEQ> seqs = new ArrayList<>();
        for (TypeDefinition tyDef: block.typeDefs)
            tyDef.accept(this);
        for (VariableDeclaration varDec: block.varDecs)
            varDec.accept(this);
        for (ProcedureOrFunctionDeclaration pfDec: block.subprogs) {
            SubprogramSegment subp = (SubprogramSegment) pfDec.accept(this);
            seqs.add(new SEQ(new LABEL(subp.label), subp.body));
            subprogs.add(new frameTuple<>(subp.body, new LABEL(subp.label), subp.frame.size())); // aux to print
        }

        return (seqs.size() > 0)?  new SEQ((Stm) block.body.accept(this), StmListToSEQ(seqs)) :
                                                                                (Stm) block.body.accept(this);
    }

    @Override
    public Object VisitProgram(Program program) {
        env.beginScope();
        currentFrame = new Frame();
        currentLevel++;
        LABEL l = new LABEL(new Label("$"+"Program__"+program.id));
        SEQ prog = new SEQ(l, (Stm) program.block.accept(this));
        subprogs.add(new frameTuple<>((Stm) program.block.body.accept(this),
                                                                        l, currentFrame.size())); // aux to print
        try {
            env.endScope();
        } catch (InvalidLevelException e) {
            e.printStackTrace();
        }

        return prog;
    }

    @Override
    public Object VisitFunctionDeclaration(FunctionDeclaration functionDeclaration) {
        SubprogramSegment subp = null;
        framesStack.push(currentFrame);
        Label procLabel = new Label("$" + functionDeclaration.nm + "_" + currentLevel);
        try {
            env.put(functionDeclaration.nm, new SubprogramLabel(procLabel));
            currentLevel++;
            env.beginScope();
            currentFrame = new Frame();
            for (FormalParameter formal: functionDeclaration.formals )
                formal.accept(this);
            Stm bdExp = (Stm) functionDeclaration.body.accept(this);
            subp = new SubprogramSegment(procLabel, currentFrame, bdExp);
            env.endScope();
        } catch (InvalidLevelException | AlreadyBoundException e) {
            e.printStackTrace();
        }
        currentLevel--;
        currentFrame = framesStack.pop();
        return subp;
    }

    @Override
    public Object VisitFunctionDesignator(FunctionDesignator functionDesignator) {
        List<Expr> exprList = new ArrayList<>();
        Label nm = ((SubprogramSegment) env.get(functionDesignator.name)).label;
        exprList.add(new CONST(0)); // Static Link is always in loc 0 of frame
        for (Expression exp : functionDesignator.actuals) {
            if (exp.accept(this) instanceof Ex)
                exprList.add(((Ex) exp.accept(this)).unEx());
            else
                exprList.add((Expr) exp.accept(this));
        }
        return new EXP(new CALL(new NAME(nm),exprList));
    }

    @Override
    public Object VisitProcedureDeclaration(ProcedureDeclaration procedureDeclaration) {
        SubprogramSegment subp = null;
        framesStack.push(currentFrame);
        Label procLabel = new Label("$" + procedureDeclaration.nm + "_" + currentLevel);
        try {
            env.put(procedureDeclaration.nm, new SubprogramLabel(procLabel));
            currentLevel++;
            env.beginScope();
            currentFrame = new Frame();
            for (FormalParameter formal: procedureDeclaration.formals )
                formal.accept(this);
            Stm bdExp = (Stm) procedureDeclaration.body.accept(this);
            subp = new SubprogramSegment(procLabel, currentFrame, bdExp);
            env.endScope();
        } catch (InvalidLevelException | AlreadyBoundException e) {
            e.printStackTrace();
        }
        currentLevel--;
        currentFrame = framesStack.pop();
        return subp;
    }

    @Override
    public Object VisitAssignStm(AssignmentStatement assignStm) {
        Exp left = (Exp) assignStm.left.accept(this),
            right = (Exp) assignStm.right.accept(this);

        return new MOVE(left.unEx(), right.unEx());
    }

    @Override
    public Object VisitCompStm(CompoundStatement compStm) {
        Stm stm_;
        List<Stm> seqs = new ArrayList<>();
        for (Statement stm : compStm.stmts) {
            stm_ = (Stm) stm.accept(this);
            if (stm_ != null) seqs.add(stm_);
        }
        if (seqs.size() > 1)
            return StmListToSEQ(seqs);
        else
            return seqs.get(seqs.size()-1);
    }

    @Override
    public Object VisitGotoStatement(GotoStatement gotoStatement) {
        LABEL l = new LABEL(new Label("Label " + gotoStatement.label + "_" + currentLevel));
        return new JUMP(l.label);
    }

    @Override
    public Object VisitIfStm(IfStatement ifStm) {
        LABEL then_label = new LABEL(new Label()), else_label = new LABEL(new Label()),
              done = new LABEL(new Label());
        if (ifStm.elsePart != null) {
            Stm condition_eval = ((Exp) ifStm.condition.accept(this)).unCx(then_label.label, else_label.label);
            return new SEQ(condition_eval,
                    new SEQ(then_label,
                            new SEQ((Stm) ifStm.thenPart.accept(this),
                                    new SEQ(else_label,
                                            (Stm) ifStm.elsePart.accept(this)))));
        } else {
            Stm condition_eval = ((Exp) ifStm.condition.accept(this)).unCx(then_label.label, done.label);
            return new SEQ(condition_eval,
                    new SEQ(then_label,
                            new SEQ((Stm) ifStm.thenPart.accept(this),
                                    done) ) );
        }
    }

    @Override
    public Object VisitLabeledStm(LabeledStatement lblStm) {
        Label l = new Label("Label " + lblStm.label + "_" + currentLevel);
        Stm stm = (Stm) lblStm.stm.accept(this);
        return new SEQ(new LABEL(l), stm);
    }

    @Override
    public Object VisitProcedureStm(ProcedureStatement procedureStm) {
        List<Expr> exprList = new ArrayList<>();
        Label nm = ((SubprogramLabel) env.get(procedureStm.name)).label;
        exprList.add(new CONST(0)); // Static Link is always in loc 0 of frame
        for (Expression exp : procedureStm.actuals) {
            if (exp.accept(this) instanceof Ex)
                exprList.add(((Ex) exp.accept(this)).unEx());
            else
                exprList.add((Expr) exp.accept(this));
        }
        return new EXP(new CALL(new NAME(nm),exprList));
    }

    @Override
    public Object VisitWhileStm(WhileStatement whileStm) {
        LABEL start = new LABEL(new Label()), body = new LABEL(new Label()), done = new LABEL(new Label());
        CJUMP cond_jmp = new CJUMP(CJUMP.EQ, ((Cx) whileStm.condition.accept(this)).unEx(),
                                                                        new CONST(1), body.label, done.label);
        JUMP jmp_start = new JUMP(start.label);
        return new SEQ(start,
                new SEQ(cond_jmp,
                        new SEQ(body,
                                new SEQ((Stm) whileStm.body.accept(this),
                                        new SEQ(jmp_start, done)))));
    }

    @Override
    public Object VisitIdExpression(IdExpression idExpression) {
        Binding var =  env.get(idExpression.name);
        if (var instanceof Var) {
            if (((Var) var).nestingLevel == currentLevel)
                return new Ex(new MEM(new BINOP(BINOP.PLUS,
                        new TEMP(framePointer),
                        new CONST(((Var) var).frameLoc))));
            else {
                int diff = ((Var) var).nestingLevel - currentLevel;
                BINOP level = new BINOP(BINOP.PLUS, new TEMP(framePointer), new CONST((((Var) var)).frameLoc));
                for (int i = 1; i <= diff; i++)
                    level = new BINOP(BINOP.PLUS, new CONST(framesStack.get(framesStack.size() - i).size()), level);
                return new Ex(new MEM(level));
            }
        } else if (var instanceof Cons) {
            return new Ex(new CONST(((Cons) var).ord));
        } else { // instance of subprogram, function return
            return new Ex(new TEMP(this.functionReturn));
        }

    }

    @Override
    public Object VisitVariableDeclaration(VariableDeclaration variableDeclaration) {
        Rep rep = (Rep) variableDeclaration.ty.accept(this);
        int loc = currentFrame.alloc(rep.size());
        try {
            env.put(variableDeclaration.id, new Var(loc, rep, currentLevel));
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // nop

    @Override
    public Object VisitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator) {
        return null;
    }

    @Override
    public Object VisitBinaryBooleanOperator(BinaryBooleanOperator binaryBooleanOperator) {
        return null;
    }

    @Override
    public Object VisitRelationalOperator(RelationalOperator relationalOperator) {
        return null;
    }

    @Override
    public Object VisitSign(Sign sign) {
        return null;
    }

    @Override
    public Object VisitEmptyStm(EmptyStatement eStm) {
        return null;
    }

    @Override
    public Object VisitStringLiteral(StringLiteral stringLiteral) {
        // TODO Auto-generated method stub
        return null;
    }
}
