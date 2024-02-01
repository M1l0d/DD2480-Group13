# DD2480-Group13
Repository containing labs in the course DD2480 Software Engineering Fundamentals

## Assignment #1: DECIDE
This is a part of a hypothetical anti-ballistic missile system, where the function DECIDE() decides whether an interceptor should be launched or not, based on different input paremeters. 

## The files

### CMV.java
Contains all the LIC functions and their helper functions.

```sh
public boolean licX()
```
There is one for each of the 15 LICS.
Returns true if condition is true, otherwise false.

#### Helper functions
```sh
public double computeAngle()
```
Returns the angle, in radians, at the middle points of three coordinates.

```sh
public boolean isTriangleValid()
```
Returns whether three data points constructs a triangle that fulfills the triangle inequality theorem.

```sh
public boolean collinear()
```
Returns true if the data points are collinear, false otherwise.

```sh
   public double calculateTriangleArea()
```
Returns the area of a triangle computed of three data points.

```sh
 public double calcDistance()
```
Returns the distance between two data points.

```sh
 public double calculateCircumCircleRadius()
```
Returns the radius of the circumcircle of a triangle.

```sh
public boolean pointInsideCircle()
```
Returns true if the data points are inside the circle, false otherwise.

### CMVTest.java
Contains unit tests for all the LIC functions. 

```sh
    @Before
    public void setUp()
        Parameters parameters = new Parameters();
        emptyCMV = new CMV(parameters);
```
The setup for the tests. 

```sh
   @Test
    public void lic0ReturnsFalseIfDistanceBetweeenTwoConsecutiveDataPointsLessThanLENGTH1(){
```
Example of test for lic0 which returns false if the distance between two consecutive data points is less then the input LENGTH1. Similar tests are done to test different relevant conditions for all 15 LIC's. 

### CompType.java
Contains LT, GT, EQ, however, not used in this assignment. 

### Connectors.java
Contains the connectors used in the LCM - ANDD, ORR and NOTUSED

### LaunchInterceptor.java
Contains the main function, the matrixes and the decide function.

### Parameters.java
Contains all the input parameters used in the LIC functions.

### How it works
The function DECIDE() will generate a boolean signal to determine whether an interceptor should be launced or not. This is based upon relevant signals from different Launch Interceptor Conditions (LIC's). DECIDE() determines whether each of fifteen different LIC's is true for an input set of up to 100 planar data points representing radar echoes. An Conditions Met Vector (CMV) is used to store the corresponding boolean values true or false for each of the 15 different LIC's. An input Logical Connector Matric (LCM), an 15x15 symmetric matric with elements valued ANDD, ORR, or NOTUSED, defines which individual LIC must be considered jointly in some way. The combination of LCM and CMV is stored in another 15x15 matrix, the Preliminary Unlocking Matric (PUM). Another input, the Preliminary Unlocking Vector (PUV) represents which LIC actually matters in the particular launch determinatin. Each element of the UB indicates how to combine the PUM

The input Logical Connector Matrix (LCM), defines which individual LIC’s must be considered jointly in some way. The LCM is a 15x15 symmetric matrix with elements valued ANDD, ORR, or NOTUSED. The combination of LCM and CMV is stored in the Preliminary Unlocking Matrix (PUM), a 15x15 symmetric matrix.
Another input, the Preliminary Unlocking Vector (PUV) represents which LIC actually matters in this particular launch determination. Each element of the UV indicates how to combine the PUM values to form the corresponding element of the Final Unlocking Vecktor (FUV), an 15-element vector. If, and only if, all the values in the FUV are true, should the launch-unlock signal be generated.

## Roadmap 
- Create main file [X]
- Add all LIC's [X]
- Add testing to all LIC's [X]
- Refactor code [X]
- Add PUM [X]
- Add FUV []
- Add Launch []
- Add overarching tests []
- Add README [X]

## Statement of contributions:
Apart from these tasks each member had, we also helped each other wherever needed. Furthermore, we all discussed different problems and solutions to them with each other to make sure everyone understood the tasks at hand.

### Alexander Widman - [AlexWidman](https://github.com/AlexWidman) :
Co-author for the main LaunchInterceptor.java file and for files Parameters.java, Connectors.java & CompType.java.
Wrote code for LICs 0, 5, 10 with tests.
Co-author for refactoring all of the code.
Co-author for README.md and other documentation.

### Alva Sundström - [alvasundstrom](https://github.com/alvasundstrom) :
Co-author for the main LaunchInterceptor.java file and for files Parameters.java, Connectors.java & CompType.java.
Wrote code for LICs 1, 6, 11 with tests.
Wrote general code for unit-tests.
Co-author for refactoring all of the code.
Wrote PUM.java with tests.

### Annie Kihlert - [kihlert](https://github.com/kihlert) :
Co-author for the main LaunchInterceptor.java file and for files Parameters.java, Connectors.java & CompType.java.
Wrote LICs 3, 8, 13 with tests.
Co-author for refactoring all of the code.
Co-author for README.md and other documentation.

### Milad Sarbandi Farhani - [M1l0d](https://github.com/M1l0d) :
Co-author for the main LaunchInterceptor.java file and for files Parameters.java, Connectors.java & CompType.java.
Wrote code for LICs 2, 7, 12 with tests.
Co-author for refactoring all of the code.
Wrote final decide() function and main test cases.

### Tomas Weldetinsae - [tywe00](https://github.com/tywe00) :
Co-author for the main LaunchInterceptor.java file and for files Parameters.java, Connectors.java & CompType.java.
Wrote code for LICs 4, 9, 14 with tests.
Co-author for refactoring all of the code.
Wrote FUV.java with tests.
