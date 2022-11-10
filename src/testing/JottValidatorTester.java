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
        testCases.add(new TestCase("invalid parameter function call (error)", "funcCallParamInvalid.jott", true));
        testCases.add(new TestCase("function not defined (error)", "funcNotDefined.jott", true));
        testCases.add(new TestCase("function return in an expression (error)", "funcReturnInExpr.jott", true));
        testCases.add(new TestCase("function wrong parameter type (error)", "funcWrongParamType.jott", true));
        testCases.add(new TestCase("hello world", "helloWorld.jott", false));
        testCases.add(new TestCase("if stmt returns ", "ifStmtReturns.jott", false));
        testCases.add(new TestCase("larger valid", "largerValid.jott", false));
        testCases.add(new TestCase("main returning a double (error)", "mainReturnNotInt.jott", true));
        testCases.add(new TestCase("mismatched return (error)", "mismatchedReturn.jott", true));
        testCases.add(new TestCase("missing function parameters (error)", "missingFunParams.jott", true));
        testCases.add(new TestCase("missing main (error)", "missingMain.jott", true));
        testCases.add(new TestCase("missing return (error)", "missingReturn.jott", true));
        testCases.add(new TestCase("no return if stmt", "noReturnIf.jott", false));
        testCases.add(new TestCase("no return in while loop", "noReturnWhile.jott", false));
        testCases.add(new TestCase("provided example 1", "providedExample1.jott", false));
        testCases.add(new TestCase("main returns an id", "returnId.jott", false));
        testCases.add(new TestCase("valid loop", "validLoop.jott", false));
        testCases.add(new TestCase("void return (error)", "voidReturn.jott", true));
        testCases.add(new TestCase("using while keyword as an id (error)", "whileKeyword.jott", true));
    }

    private static boolean testValidator(TestCase tc) {
        ArrayList<Token> tokens = JottTokenizer.tokenize("phase3TestCases/" + tc.fileName);
        assert tokens != null;
        JottTree parseTree = JottParser.parse(tokens);
        assert parseTree != null;
        return parseTree.validateTree(null, null);
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
