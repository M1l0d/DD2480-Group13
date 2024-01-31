# DD2480-Group13
Repository containing labs in the course DD2480 Software Engineering Fundamentals

Assignment #1: DECIDE

1. Tell me what the thing is
DECIDE() will generate a boolean signal which determines whether an interceptor should be launched based upon input radar tracking information. This radar tracking information is available at the instant the function is called.
DECIDE() determines which combination of the several possible Launch Interceptor Condi- tions (LIC’s) are relevant to the immediate situation. The interceptor launch button is normally considered locked; only if all relevant combinations of launch conditions are met will the launch- unlock signal be issued.
DECIDE() determines whether each of fifteen LIC’s is true for an input set of up to 100 planar data points representing radar echoes. The fifteen elements of a Conditions Met Vector (CMV) will be assigned boolean values true or false; each element of the CMV corresponds to one LIC’s condition.
The input Logical Connector Matrix (LCM), defines which individual LIC’s must be consid- ered jointly in some way. The LCM is a 15x15 symmetric matrix with elements valued ANDD, ORR, or NOTUSED. The combination of LCM and CMV is stored in the Preliminary Unlocking Matrix (PUM), a 15x15 symmetric matrix.
Another input, the Preliminary Unlocking Vector (PUV) represents which LIC actually matters in this particular launch determination. Each element of the UV indicates how to combine the PUM
1
values to form the corresponding element of the Final Unlocking Vector (FUV), a 15-element vector. If, and only if, all the values in the FUV are true, should the launch-unlock signal be generated.

2. Tell me what it does

3. Don't tell me how it works

4. Grant me the right to use it

5. Don’t change it

The files:
LaunchInterceptor.java
LaunchInterceptorTest.java

The functions:


Contributions:
The issues have been mutually devided between the groups with the exact contributions stated below.
Alva: 
Tomas: 
Alexandio: 
Annie: 
Milad: 
