# COMP1110/6710 Final Exam 2020 S1

The questions provided here comprise the final exam for COMP1110/6710 in Semester
1 of 2020.   These are provided for you as sample questions.  Notice that some
questions have multiple variations, labelled 'A', 'B', etc...   When you sit a
real exam, you would only get one variation.

_Please note that subsequent exams may have different structures and be different in style._

The text below this line is taken from the Semester 1 2020 exam.

---

Exam period: 3 hours 15 minutes

You should attempt to answer all questions.
Questions total 80 marks. Questions are not of equal value.

You will find six questions in the exam.  The first five questions have coding elements, totalling eight subquestions, and for each of these, you will find a separate Java class with FIXME notes indicating what you need to do.  The number of marks per question varies.
There is one unit test provided for each question which you may use to
test your answer.   If a question is worth five marks, you will find that there are five unit tests.

For coding questions, the specification of each method you are required to implement is provided in the Javadoc comment immediately above the method. You must adhere to that specification.
You may use any part of the [Java standard API](https://docs.oracle.com/en/java/javase/13/docs/api/index.html) in your solutions, but you may not use other non-standard libraries (such as Apache libraries).
All coding questions are auto-graded; you will be graded according to how many tests you pass. The tests used to grade your work may differ slightly from the tests provided in this repository. Incorrect answers that pass tests will be penalized accordingly.

Fork this repo within GitLab and then clone your copy from within IntelliJ.
Once you have forked the exam:
- answer each question by completing the provided answer file(s) according to the instructions you will find in the file README.md;
- test your answer to each programming question using the provided unit tests; and
- after you complete each question, commit and push your work to GitLab.

The last commit that you push to GitLab before the end of the exam will be used for marking. **Any commits pushed after the end time of the exam will be ignored and will not count towards your exam mark.** You may commit and push as many times as you wish during the exam time; only the final on-time commit will be marked.

## Question 1  [15 Marks] 

### Imperative Programming in Java
 
### Q1.1 A  [5 Marks]  Basic Imperative Programming

Using the incomplete template for [Q1Tao.java](src/comp1110/exam/Q1Tao.java), complete the unimplemented method `tao()`. Use the tests provided to test your solution, and then commit and push your changes to GitLab.

### Q1.1 B  [5 Marks]  Basic Imperative Programming

Using the incomplete template for [Q1Factors.java](src/comp1110/exam/Q1Factors.java), complete the unimplemented method `factors()`. Use the tests provided to test your solution, and then commit and push your changes to GitLab.

### Q1.2 A [5 Marks]  Recursion

Using the incomplete template for [Q1Rain.java](src/comp1110/exam/Q1Rain.java), complete the unimplemented method `avg()`. Use the tests provided to test your solution, and then commit and push your changes to GitLab.

### Q1.2 B [5 Marks]  Recursion

Using the incomplete template for [Q1FamilyTree.java](src/comp1110/exam/Q1FamilyTree.java), complete the unimplemented method `getAncestry()`. Use the tests provided to test your solution, and then commit and push your changes to GitLab.


### Q1.3 A [5 Marks]  Abstract Data Types, Search

Using the incomplete template for [Q1CropRotation.java](src/comp1110/exam/Q1CropRotation.java), complete the unimplemented method `getAllRotations()`. Use the tests provided to test your solution, and then commit and push your changes to GitLab.

### Q1.3 B [5 Marks]  Abstract Data Types, Search

Using the incomplete template for [Q1MealPlan.java](src/comp1110/exam/Q1MealPlan.java), complete the unimplemented method `getAllRotations()`. Use the tests provided to test your solution, and then commit and push your changes to GitLab.

### Q1.3 C [5 Marks]  Abstract Data Types, Search

Using the incomplete template for [Q1Sudoku.java](src/comp1110/exam/Q1Sudoku.java), complete the unimplemented method `getAllRotations()`. Use the tests provided to test your solution, and then commit and push your changes to GitLab.

## Q2  [5 Marks]  File I/O

Using the incomplete template for [Q2Awk.java](src/comp1110/exam/Q2Awk.java), complete the unimplemented method `extractColumnCSV()`. Use the tests provided to test your solution, and then commit and push your changes to GitLab.

## Question 3  [25 Marks]  Wiki
 
### Q3.1  [20 Marks]  Software Implementation
 
 Using the incomplete template for [Q3Wiki.java](src/comp1110/exam/Q3Wiki.java), complete *all unimplemented methods*.
 
 You must complete your solution as **a single file, [Q3Wiki.java](src/comp1110/exam/Q3Wiki.java)**. You are encouraged to create additional classes as required to solve the problem; any additional classes must be implemented as **nested classes** within the `Q3Wiki` class.
 
 Use the tests provided to test your solution, and then commit and push your changes to GitLab.
 
 ### Q3.2  [5 Marks]  Software Testing
 
 Using the incomplete template for [Q3GetMaxArticlesInCategoryTest.java](src/comp1110/exam/Q3GetMaxArticlesInCategoryTest.java), write one or more unit tests for the `getMaxArticlesInCategory()` method. You must complete your solution as a single file, `Q3GetMaxArticlesInCategoryTest.java`. When writing the tests, you may assume that the provided methods in the `Q3Wiki` class are correctly implemented.
 
 The specification of the `getMaxArticlesInCategory()` method is provided in the Javadoc comment immediately above the method. This question is auto-graded; your tests will be run against multiple implementations of the `getMaxArticlesInCategory()` method, some of which are correct and some incorrect. You will be graded according to how many correct implementations successfully pass your tests and how many incorrect implementations fail your tests.
 
 Once you have completed your tests, commit and push your changes to GitLab.
 
 ## Question 4 A [10 Marks]  Data Structures
 
 Using the incomplete template for [Q4ArrayQueue.java](src/comp1110/exam/Q4ArrayQueue.java), complete the unimplemented methods `isEmpty()`, `enqueue()`, `dequeue()`, `first()`, `contains()`, and `toString()`.
Your solution must implement the data structure from first principles (as was done in lectures), avoiding use of Java's collection classes such as `java.util.List`.
 *Solutions that ignore this requirement will be penalized accordingly.*
 
 Use the tests provided to test your solution, and then commit and push your changes to GitLab.
 
  ## Question 4 B [10 Marks]  Data Structures
  
  Using the incomplete template for [Q4ArrayStack.java](src/comp1110/exam/Q4ArrayStack.java), complete the unimplemented methods `isEmpty()`, `push()`, and `pop()`, `peek()`, `contains()`, and `toString()`.
 Your solution must implement the data structure from first principles (as was done in lectures), avoiding use of Java's collection classes such as `java.util.Stack`.
  *Solutions that ignore this requirement will be penalized accordingly.*
  
  Use the tests provided to test your solution, and then commit and push your changes to GitLab.
  
 
 ## Question 5 A  [5 Marks]  Hash Codes
 
 Using the incomplete template for [Q5PackageSpecification.java](src/comp1110/exam/Q5PackageSpecification.java), complete the overridden methods `hashCode()` and `equals()` correctly, according to the contract of these methods. Your hash function should be as uniform as possible. Use the tests provided to test your solution, and then commit and push your changes to GitLab.
 
 ## Question 5 B [5 Marks]  Hash Codes
  
 Using the incomplete template for [Q5StringHash.java](src/comp1110/exam/Q5StringHash.java), complete the unimplemented method `hash()`, **without using Java's built-in `hashCode()` method**. Your hash function should be as uniform as possible. Use the tests provided to test your solution, and then commit and push your changes to GitLab.
  
 
 ## Question 6  [20 Marks]  Threads, JavaFX, Complexity, Grammars
 For each of the following multiple-choice questions, identify the choice that provides the best answer.  Record your choices in the file [q-6.csv](q-6.csv), one line per question.
 For example, to answer "a" to question 6.1, "d" to question 6.2, "a" to question 6.4, and leave question 6.3 blank, you would record the following in q-6.csv:
 ````
6.1,a
6.2,d
6.3,
6.4,a
````
 
 Each question that is correctly answered gains you 5 marks, each question answered incorrectly loses you 1 mark, a question left unanswered neither loses nor gains marks.
 The final mark for this question is calculated by bounding the sum of marks between 0 and 20. For example, if you answered all questions correctly you would gain 20 for this question. If you answer 1 correctly and 2 incorrectly you would gain 3/20 for this question.
 
 ### Q6.1  [5 Marks]  Threads
 Given the following four Java code fragments, identify the one least likely to contain a race condition.
 
 a)
 ````
private void work(int jobnum) {
     try {
 	Thread.sleep(1);
     } catch (InterruptedException e) {
 	e.printStackTrace();
     }
 }
 
 private int incJobNum() {
     return jobnumber++;
 }
 
 @Override
 public void run() {
    int jobnum;
    while ((jobnum = incJobNum()) < WORK_ITEMS) {
        work(jobnum);
    }
 }
````

b)
```` 	
 private void work(int jobnum) {
     try {
 	Thread.sleep(1);
     } catch (InterruptedException e) {
 	e.printStackTrace();
     }
 }
 
 synchronized
 private int incJobNum() {
     return jobnumber++;
 }
 
 @Override
 public void run() {
    int jobnum;
    while ((jobnum = incJobNum()) < WORK_ITEMS) {
        work(jobnum);
    }
 }
 ````

c)
````	
 synchronized
 private void work(int jobnum) {
     try {
 	Thread.sleep(1);
     } catch (InterruptedException e) {
 	e.printStackTrace();
     }
 }
 
 private int incJobNum() {
     return jobnumber++;
 }
 
 @Override
 public void run() {
    int jobnum;
    while ((jobnum = incJobNum()) < WORK_ITEMS) {
        work(jobnum);
    }
 }
 ````

d)
````	
 private void work(int jobnum) {
     Thread.sleep(1);
 }
 
 private int incJobNum() {
     return jobnumber++;
 }
 
 @Override
 public void run() {
    int jobnum;
    while ((jobnum = incJobNum()) < WORK_ITEMS) {
        work(jobnum);
    }
 }
````

### Q6.2  [5 Marks]  JavaFX

Consider the code below.
````
System.out.println("A");
scene.setOnKeyTyped(event -> {
  System.out.println(event.getCharacter());
  if (event.getCharacter().equals("Q"))
  Platform.exit();
});
System.out.println("B");
stage.show();
````

From among the following, select the statement that most accurately describes the output of the code above when the program containing it is run and the `K` key is pressed followed by the `Q` key.

a) `A` will be printed out followed by `K`.

b) `A` will be printed out followed by `K` then `Q`.

c) `A` will be printed out.

d) `A` will be printed out followed by `B`.

e) `A` will be printed out followed by `K` then `Q`, then `B`.

f) `A` will be printed out followed by `B` then `K`, then `Q`.
                            

### Q6.3 A [5 Marks]  Complexity

Consider the method `f()` below.
````
static List<Integer> f(List<Integer> in) {
    if (in.size() <= 1) return in;
    int size = in.size();
    List<Integer> l = f(in.subList(0, size / 2));
    List<Integer> r = f(in.subList(size / 2, size));
    int lf = 0;
    int rf = 0;
    List<Integer> result = new ArrayList<>(size);
    while (lf < l.size() || rf < r.size()) {
        if (lf == l.size() || (rf < r.size() &&
                r.get(rf) < l.get(lf))) {
            result.add(r.get(rf++));
        } else {
            result.add(l.get(lf++));
        }
    }
    return result;
}
````

Which of the following statements is correct?

a) The time complexity of `f()` with `in` of size n is *O(n^2)*.

b) The time complexity of `f()` with `in` of size n is *O(n log n)*.

c) The time complexity of `f()` with `in` of size n is *O(n)*.

d) The time complexity of `f()` with `in` of size n is *O(log n)*.

### Q6.3 B [5 Marks]  Complexity

Consider the method `f()` below.
````
<T extends Comparable<T>> void f(List<T> l) {
    boolean s;
    do {
        s = false;
        for (int i = 1; i < l.size(); i++) {
            if (l.get(i).compareTo(l.get(i - 1)) < 0) {
                T t = l.get(i);
                l.set(i, l.get(i - 1));
                l.set(i - 1, t);
                s = true;
            }
        }
    } while (s);
}
````

Which of the following statements is correct?

a) For an input of length N, `f()` has a worst-case complexity of *O(n^2)* and a best-case complexity of *O(n)*.

b) For an input of length N, `f()` has an average complexity of *O(n log n)* and a best-case complexity of *O(n)*.

c) For an input of length N, `f()` has a worst-case complexity of *O(n^2)* and a best-case complexity of *O(n log n)*.

d) For an input of length N, `f()` has a worst-case complexity of *O(n)* and a best-case complexity of *O(log n)*.

### Q6.3 C [5 Marks]  Complexity                         

```
<T extends Comparable<T>> void h(List<T> l, int x, int y) {
    if (x < y) {
        T t = l.get(x);
        int m = y;
        for (int i = y; i >= x; i --) {
            if (l.get(i).compareTo(t) > 0) {
                T r = l.get(i);
                l.set(i, l.get(m));
                l.set(m, r);
                m --;
            }
        }
        T s = l.get(x);
        l.set(x, l.get(m));
        l.set(m, s);
        h(l, x, m - 1);
        h(l, m + 1, y);
    }
}
<T extends Comparable<T>> void g(List<T> l) {
    h(l, 0, l.size()-1);
}
```

Which of the following statements is correct?
 
 a) For an input of length N, `h()` has a worst-case complexity of *O(n^2)* and a best-case complexity of *O(log n)*.
 
 b) For an input of length N, `h()` has an average complexity of *O(n log n)* and a best-case complexity of *O(n)*.
 
 c) For an input of length N, `h()` has a worst-case complexity of *O(n^2)* and a best-case complexity of *O(n log n)*.
 
 d) For an input of length N, `h()` has a worst-case complexity of *O(n^2)* and a best-case complexity of *O(n)*.


### Q6.4 A [5 Marks]  Grammars

This question relates to sentences (chemical formulae) in a language defined by a simple EBNF grammar.
For reference, some symbols of EBNF are as follows:

````
= defines a production rule
, concatenation
| alternation / choice
[...] optional - zero or one
{...} optional - zero or more
(...) grouping
- exception
; terminates a production rule.
````

Given the following EBNF grammar:

````
formula=unit,{unit};
unit=repeat|group;
repeat=element,[number];
element='H'|'C'|'N'|'O'|'Cl';
number=(nzdigit,{digit})-'1'|'n';
nzdigit='1'|'2'|'3'|'4'|'5'|'6'|'7'|'8'|'9';
digit=nzdigit|'0';
group='(',repeat,{repeat},')',number;
````

Identify which of the following strings cannot be produced by the grammar.
    
a) 
````
(CH3)3CH
````

b)
````
NH(CH2)3CHCOOH
````

c)
````
CH3CH(NH2)COOH
````

d)
````
(C2H3Cl)n
````

### Q6.4 B [5 Marks]  Grammars

This question relates to sentences in a language defined by a simple EBNF grammar.
For reference, some symbols of EBNF are as follows:

````
= defines a production rule
, concatenation
| alternation / choice
[...] optional - zero or one
{...} optional - zero or more
(...) grouping
- exception
; terminates a production rule.
````

Given the following EBNF grammar:

````
game=(complete|partial),{' ',complete},[' ',partial];
partial=number,' ',move;
complete=partial,' ',move;
number=nzdigit,{digit},'.';
nzdigit='1'|'2'|'3'|'4'|'5'|'6'|'7'|'8'|'9';
digit=nzdigit|'0';
move=[piece],[rank],[file],['x'],rank,file;
piece='K'|'Q'|'R'|'B'|'N';
rank='a'|'b'|'c'|'d'|'e'|'f'|'g'|'h';
file='1'|'2'|'3'|'4'|'5'|'6'|'7'|'8';
````

Identify which of the following strings cannot be produced by the grammar.

a)
````
6. Bf6 c3xe 7. Nc3
````

b) 
````
1. e4 e5 2. Bf3 Nc6 3. Nb5
````

c)
````
2. e4 d5 1. Nf3 dxe4
````

d)
````
3. Bb5 a6 4. Bxc6 dxc6
````


