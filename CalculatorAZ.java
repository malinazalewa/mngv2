import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.util.Stack;

public class CalculatorAZ {
    static Stack<Integer> stack = new Stack<>();
    static int result;

    public static void main(String[] args) {
        String expression = "2 + 1 - 4 * 3 / 2";
        System.out.println("Expression: " + expression);
        CharStream input = CharStreams.fromString(expression);
        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.expression();

        CalculatorListener listener = new CalculatorBaseListener() {
            @Override
            public void exitAtom(CalculatorParser.AtomContext ctx) {
                int value = Integer.parseInt(ctx.INTEGER().getText());
                stack.push(value);
                System.out.println("Atom: " + value + ", Stack: " + stack);
                super.exitAtom(ctx);
            }

            @Override
            public void exitExpression(CalculatorParser.ExpressionContext ctx) {
                if (ctx.PLUS() != null) {
                    if (stack.size() >= 2) {
                        int rightOperand = stack.pop();
                        int leftOperand = stack.pop();
                        int result = leftOperand + rightOperand;
                        stack.push(result);
                    } else {
                        System.out.println("zly znak dzialania");
                    }
                } else if (ctx.MINUS() != null) {
                    if (stack.size() >= 2) {
                        int rightOperand = stack.pop();
                        int leftOperand = stack.pop();
                        int result = leftOperand - rightOperand;
                        stack.push(result);
                    } else {
                        System.out.println("zly znak dzialania");
                    }
                }
                System.out.println("Exit Expression: " + ctx.getText() + ", Stack: " + stack);
                super.exitExpression(ctx);
            }

            @Override
            public void exitMultiplyingExpression(CalculatorParser.MultiplyingExpressionContext ctx) {
                if (ctx.TIMES() != null) {
                    if (stack.size() >= 2) {
                        int rightOperand = stack.pop();
                        int leftOperand = stack.pop();
                        int result = leftOperand * rightOperand;
                        stack.push(result);
                    } else {
                        System.out.println("zly znak dzialania");
                    }
                } else if (ctx.DIV() != null) {
                    if (stack.size() >= 2) {
                        int rightOperand = stack.pop();
                        int leftOperand = stack.pop();
                        int result = leftOperand / rightOperand;
                        stack.push(result);
                    } else {
                        System.out.println("zly znak dzialania");
                    }
                }
                System.out.println("Exit MultiplyingExpression: " + ctx.getText() + ", Stack: " + stack);
                super.exitMultiplyingExpression(ctx);
            }
        };

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        if (!stack.isEmpty()) {
            result = stack.pop();
        } else {
            result = 0;
        }

        System.out.println("Result: " + result);
    }
}