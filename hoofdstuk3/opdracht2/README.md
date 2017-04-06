# Purpose
Run two `Auto` instances and one `Brug` instance, each in separate threads. Use a java.util.concurrent.locks.ReentrantLock.ReentrantLock. Obtain the lock of the objects on related methods. In this way the 2 autos only cross the bridge when it is closed.

# How to run the applet
To see the applet in action you can use AppletViewer which is included with JDKs. If you have the JDK installed and the location of the executables is in your path you can execute the terminal command:

```
appletviewer brug.html
```
