package testing;

import parser.JottParser;
import parser.nodes.JottTree;
import tokenizer.JottTokenizer;
import utils.Token;

import java.util.ArrayList;

public class JottValidatorTester {

    static ArrayList<TestCase> testCases;

    private static class TestCase{
        String testName;
        String fileName;
        boolean error;

        public TestCase(String testName, String fileName, boolean error) {
            this.testName = testName;
            this.fileName = fileName;
            this.error = error;
        }
    }

    private static void createTestCases() {
        testCases = new ArrayList<>();
//        testCases.add(new TestCase("invalid parameter function call", "funcCallParamInvalid.jott", false));
//        testCases.add(new TestCase("function not defined (error)", "funcNotDefined.jott", true));
        testCases.add(new TestCase("function return in an expression (error)", "funcReturnInExpr.jott", true));
//        testCases.add(new TestCase("provided writeup example4 (error)", "providedExample4.jott", true));
//        testCases.add(new TestCase("provided writeup example5 (error)", "providedExample5.jott", true));
//        testCases.add(new TestCase("hello world", "helloWorld.jott", false));
//        testCases.add(new TestCase("1foo error (error)", "1foo.jott", true));
//        testCases.add(new TestCase("return <id> type mismatch", "returnId.jott", false));
//        testCases.add(new TestCase("type:var error (error)", "paramOrderSwapped.jott", true));
//        testCases.add(new TestCase("missing expr (error)", "missingExp.jott", true));
//        testCases.add(new TestCase("missingBrace (error)", "missingBrace.jott", true));
//        testCases.add(new TestCase("elseif without if (error)", "elseIfNoIf.jott", true));
//        testCases.add(new TestCase("missing return", "missingReturn.jott", false));
//        testCases.add(new TestCase("Void not valid param type (error)", "voidParam.jott", true));
//        testCases.add(new TestCase("function not defined", "funcNotDefined.jott", false));
//        testCases.add(new TestCase("mismatch return type", "mismatchedReturn.jott", false));
//        testCases.add(new TestCase("function call param type not matching", "funcCallParamInvalid.jott", false));
//        testCases.add(new TestCase("single expression program (error)", "singleExpr.jott", true));
//        testCases.add(new TestCase("valid while loop", "validLoop.jott", false));
//        testCases.add(new TestCase("missing main", "missingMain.jott", false));
//        testCases.add(new TestCase("main must be integer", "mainReturnNotInt.jott", false));
//        testCases.add(new TestCase("i_expr relop d_expr function return", "funcReturnInExpr.jott", false));
//        testCases.add(new TestCase("invalid asmt stmt (error)", "invalidAsmtStmt.jott", true));
//        testCases.add(new TestCase("missing comma in func_def_params (error)", "missingCommaParams.jott", true));
//        testCases.add(new TestCase("while is keyword, cannot be used as id", "whileKeyword.jott", false));
//        testCases.add(new TestCase("expr by itself (error)", "loneExpr.jott", true));
//        testCases.add(new TestCase("code after return (error)", "codeAfterReturn.jott", true));
//        testCases.add(new TestCase("lone minus (error)", "loneMinus.jott", true));
//        testCases.add(new TestCase("else without if (error)", "elseNoIf.jott", true));
//        testCases.add(new TestCase("missing closing } (error)", "missingClosing.jott", true));
    }

    private static boolean testValidator(TestCase tc) {
        ArrayList<Token> tokens = JottTokenizer.tokenize("phase3TestCases/" + tc.fileName);
        assert tokens != null;
        JottTree parseTree = JottParser.parse(tokens);
        if (parseTree != null) {
            return parseTree.validateTree(null, null);
        } else {
            System.err.println("false!!");
            return false;
        }
    }

    public static void main(String[] args) {
        createTestCases();

        for (TestCase tc: testCases) {
            System.out.println(tc.testName);
            if (!tc.error && testValidator(tc)) {
                System.out.println("PASSED");
            }
            else if (!tc.error && !testValidator(tc)) {
                System.out.println("FAILED (test failed but was expected a pass)");
            }
            else if (tc.error && !testValidator(tc)) {
                System.out.println("PASSED");
            }
            else if (tc.error && testValidator(tc)) {
                System.out.println("FAILED (test passed but was expected to fail)");
            }
            System.out.println();
        }

    }
}
