.source T_if_eq_10.java
.class public dot.junit.opcodes.if_eq.d.T_if_eq_10
.super java/lang/Object


.method public <init>()V
.limit regs 1

       invoke-direct {v0}, java/lang/Object/<init>()V
       return-void
.end method

.method public run(II)Z
.limit regs 8

       if-eq v6, v7, Label10
       const/4 v6, 0
       return v6

Label10:
       const/4 v6, 1
       return v6
.end method
