# Constraint Satisfaction Problem Solver

## Details
 - Creator: Kaleb Gaar
 - Last Updated: 2/2/18
 - Class: CSCE 421

## Description

This project takes XML files of the XCSP 2.0 file type and transforms them into a custom CSP object. It parses the file using the InstanceParser class from the abscon toolset. It also has support to use Arc Consistency Algorithms to simplify and solve the CSP.

There are two main parts of this piece of the project: the individual components of a CSP object and the solving functions. The CSP object is made up of two lists: A list of variables and a list of constraints.

## Checkpoint Status:

I am not yet finished with checkpoint 1, so I do not expect full credit.

### What is done:

 - Implementation of Node Consistency and AC1
 - Support for Extension constraints
 - AC Custom Queue object created
 - Start of Algorithm Analysis class
 
### What is not done:

 - Support for Intension constraints
 - Finalized Algorithm Analysis class
 - Uploading of information to CSCE 421 wiki.

## Help Recieved:

 - Abscon System: In addition to the parser, inspiration for the individual data structures was taken from this system. These structures, however, are not clones of each other, and much of the class design occurred before reviewing the Abscon files.
 - Sam Flint helped me with my build.xml and makefile.
 - Algorithm help from class slides and text book.