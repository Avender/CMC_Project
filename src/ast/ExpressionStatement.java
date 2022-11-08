package ast;

public class ExpressionStatement extends Statement
{
    public Expression expression;

    public ExpressionStatement(Expression expression){
        this.expression = expression;
    }
}
