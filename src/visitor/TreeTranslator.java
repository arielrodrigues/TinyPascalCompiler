package visitor;

import IntermediateRepresentation.Binding.*;
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

import java.util.ArrayList;
import java.util.List;

public class TreeTranslator implements PascalVisitor {

    int currentLevel;
    Frame currentFrame;

    SymbolTable<Binding> env = new SymbolTable<>();

    //Each function and procedure is translated into a SubprogramSegment, thus
    //the visit to a Program returns the correspondent List<SubprogramSegment>
    //For the sake of simplicity, we assume that the main program has a frame
    //which hosts the global variables.

    public TreeTranslator (Program prog) {
        VisitProgram(prog);
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object VisitBooleanConstant(BooleanConstant booleanConstant) {
        return booleanConstant.value? new CONST(1) : new CONST(0);
    }

    @Override
    public Object VisitCharacterConstant(CharacterConstant characterConstant) {
        return new CONST((int) characterConstant.value);
    }

    @Override
    public Object VisitSignedNumber(SignedNumber signedNumber) {
        return signedNumber.sign == Sign.PLUS?
                new CONST(signedNumber.unsNum.value) :
                    new CONST(-signedNumber.unsNum.value);
    }

    @Override
    public Object VisitUnsignedNumber(UnsignedNumber unsignedNumber) {
        return new CONST(unsignedNumber.value);
    }

    @Override
    public Object VisitBinaryArithmeticExpression(BinaryArithmeticExpression binaryArithmeticExpression) {
        //TODO
        Exp lf = (Exp) binaryArithmeticExpression.left.accept(this);
        Exp rt = (Exp) binaryArithmeticExpression.right.accept(this);

        if (binaryArithmeticExpression.op == BinaryArithmeticOperator.PLUS) {
            //return new BINOP(lf.unEx(), rt.unEx(), BINOP.PLUS);
        }

        return null;
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
        return null;
    }

    @Override
    public Object VisitBooleanLiteral(BooleanLiteral booleanLiteral) {
        return booleanLiteral.value? new Ex(new CONST(1)) : new Ex(new CONST(0));
    }

    @Override
    public Object VisitCharacterLiteral(CharacterLiteral characterLiteral) {
        return null;
    }

    @Override
    public Object VisitIndexedVariable(IndexedVariable indexedVariable) {
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
        return null;
    }

    @Override
    public Object VisitRelationalExpression(RelationalExpression relationalExpression) {
        return null;
    }

    @Override
    public Object VisitSignedExpression(SignedExpression signedExpression) {
        return null;
    }

    @Override
    public Object VisitStringLiteral(StringLiteral stringLiteral) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object VisitFormalPar(FormalPar formalPar) {
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
        }
        //TODO RETURN SEQS IN A SEQUENCIAL WAY, ADD BODY ACCEPT TO LIST AND ADD LABEL OF PROGRAM TOO - SHOW SHOW
        block.body.accept(this);
        return null;
    }

    @Override
    public Object VisitProgram(Program program) {
        LABEL l = new LABEL(new Label("$"+"Program__"+program.id));
        env.beginScope();
        SEQ prog = new SEQ(l, (Stm) program.block.accept(this));
        try {
            env.endScope();
        } catch (InvalidLevelException e) {
            e.printStackTrace();
        }

        return prog;
    }

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
    public Object VisitFunctionDeclaration(FunctionDeclaration functionDeclaration) {
        return null;
    }

    @Override
    public Object VisitFunctionDesignator(FunctionDesignator functionDesignator) {
        return null;
    }

    @Override
    public Object VisitProcedureDeclaration(ProcedureDeclaration procedureDeclaration) {
        Frame callerFrame = currentFrame;
        Label procLabel = new Label("$" + procedureDeclaration.nm + "_" + currentLevel);
        try {
            env.put(procedureDeclaration.nm, new SubprogramLabel(procLabel));
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        currentLevel++;
        env.beginScope();
        currentFrame = new Frame();
        for (FormalParameter formal: procedureDeclaration.formals )
            formal.accept(this);
        Stm bdExp = (Stm) procedureDeclaration.body.accept(this);
        SubprogramSegment subp = new SubprogramSegment(procLabel, currentFrame, bdExp);
        try {
            env.endScope();
        } catch (InvalidLevelException e) {
            e.printStackTrace();
        }
        currentLevel--;
        currentFrame = callerFrame;
        return subp;
    }

    @Override
    public Object VisitAssignStm(AssignmentStatement assignStm) {
        return null;
    }

    @Override
    public Object VisitCompStm(CompoundStatement compStm) {
        return null;
    }

    @Override
    public Object VisitEmptyStm(EmptyStatement eStm) {
        return null;
    }

    @Override
    public Object VisitGotoStatement(GotoStatement gotoStatement) {
        return null;
    }

    @Override
    public Object VisitIfStm(IfStatement ifStm) {

        return null;
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
        Label nm = ((SubprogramSegment) env.get(procedureStm.name)).label;
        exprList.add(new CONST(0)); // Static Link is always in loc 0 of frame
        for (Expression exp : procedureStm.actuals) {
            if (exp.accept(this) instanceof Ex)
                exprList.add(((Ex) exp.accept(this)).unEx());
            else
                exprList.add((Expr) exp.accept(this));
        }
        return new CALL(new NAME(nm),exprList);
    }

    @Override
    public Object VisitWhileStm(WhileStatement whileStm) {
        LABEL start = new LABEL(new Label()), body = new LABEL(new Label()), done = new LABEL(new Label());
        CJUMP cond_jmp = new CJUMP(CJUMP.EQ, (Expr) whileStm.condition.accept(this),
                                                                        new CONST(1), body.label, done.label);
        JUMP jmp_start = new JUMP(start.label);
        return new SEQ(start,
                new SEQ(cond_jmp,
                        new SEQ(body,
                                new SEQ(jmp_start,
                                        done))));
    }

    @Override
    public Object VisitIdExpression(IdExpression idExpression) {
        return env.get(idExpression.name);
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
}
