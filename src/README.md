## Project Structure
1. Parser:
   This directory contains the Jott parser implementation and the Nodes according to the 
   Grammar for Jott.
   Nodes are broke down like so:
   - /expr: contains the expression node
   - /function: contains all the nodes related to functions (ie: functionCall)
   - /operation: contains all the nodes related to operations.
   - /primitive: has all the nodes related to primitive type such as String, Double.
   - /stmt: has all the nodes related to statements, variables declaration, etc.
2. Tokenizer:
   The tokenizer directory has the jott tokenizer implementation and a few temporary test files.
3. Utils:
   This directory has the Token and TokenType classes used by the Jott tokenizer.
4. Testing:
   Contains the JottTokenizerTestCases and their tester.
   Contains the JottParserTester (the files are at the root level).
   