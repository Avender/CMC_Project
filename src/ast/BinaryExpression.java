package ast;

public class BinaryExpression extends Expression
{
    public Operator operator;
    public Expression operand0;
    public Expression operand1;

    public BinaryExpression ( Operator operator, Expression operand0, Expression operand1)
    {
        this.operator = operator;
        this.operand0 = operand0;
        this.operand1 = operand1;
    }
}
