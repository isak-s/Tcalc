# Tcalc
A terminal calculator with a recurive expession evaluator that can handle complex expressions with many parenthese.

The most common datastructure used for expression evaluation is a binary tree, where nodes are operators, and leaves are operands.

I wanted to learn more about stacks and recursion however, and therefore i set out to create an expression evaluator using a stack, from scratch.

The main challenge i faced was handling operator precedence. A binary tree does this naturally when evaluating a properly built structure, but when using a stack it becomes a bit more difficult..

My solution was to enclose all prioritized operations in parentheseses when they are pushed into the stack from a string. When the recursive algorithm then traverses the stack, it expects two operands and an operator. In the case of a parethesised expression, the resut from a recursive call becomes the operand.

In hindsight, I really think that a stack isn't the optimal data structure for a problem like this. Choosing the correct datastructure for a given problem, can make the problem dramatically easier--While choosing the wrong one can provide a fun challenge :) 
