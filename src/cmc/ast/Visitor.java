package cmc.ast;

public interface Visitor
{
    public Object visitProgram( Program program, Object arg );

    public Object visitBlock( Block block, Object arg );

    public Object visitDeclarations( Declarations declarations, Object arg );

    public Object visitVariableDeclaration( VariableDeclaration variableDeclaration, Object arg );

    public Object visitFunctionDeclaration( FunctionDeclaration functionDeclaration, Object arg );

    public Object visitStatements( Statements statements, Object arg );

    public Object visitExpressionStatement( ExpressionStatement expressionStatement, Object arg );

    public Object visitIfStatement( IfStatement ifStatement, Object arg );

    public Object visitWhileStatement( WhileStatement whileStatement, Object arg );

    public Object visitPrintStatement( PrintStatement printStatement, Object arg );

    public Object visitBinaryExpression( BinaryExpression binaryExpression, Object arg );

    public Object visitVarExpression( VarExpression varExpression, Object arg );

    public Object visitCallExpression( CallExpression callExpression, Object arg );

    public Object visitUnaryExpression( UnaryExpression unaryExpression, Object arg );

    public Object visitIntLitExpression( IntLitExpression intLitExpression, Object arg );

    public Object visitExpList( ExpList expList, Object arg );

    public Object visitIdentifier( Identifier identifier, Object arg );

    public Object visitIntegerLiteral( IntegerLiteral integerLiteral, Object arg );

    public Object visitOperator( Operator operator, Object arg );
}
