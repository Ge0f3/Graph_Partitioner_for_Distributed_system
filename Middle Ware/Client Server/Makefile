# Java Compiler
JC = javac
#Sub Directories containing the peer code and makefiles
DIRS = peer_1 peer_2 peer_3
# suffixes of input and output files
.SUFFIXES: .java .class
# compile statement 
.java.class:
	$(JC) $(JFLAGS) $*.java
# classes initialization
CLASSES = \
	CentralIndxServer.java
# main command in the Makefile
default: classes multi
# classes reference
classes: $(CLASSES:.java=.class)
# executing multiple Makefiles in sub directories
multi:
	+$(MAKE) -C peer_1
	+$(MAKE) -C peer_2
	+$(MAKE) -C peer_3
# clean statement for the root directory and the for loop to execute clean in sub directories
clean:
	+$(RM) *.class
	-for d in $(DIRS); do (cd $$d; $(MAKE) clean); done
