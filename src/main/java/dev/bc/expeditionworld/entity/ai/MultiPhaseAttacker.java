package dev.bc.expeditionworld.entity.ai;

public interface MultiPhaseAttacker {
    void setAttackState(int state);
    int getAttackState();
    void setAttackTicks(int ticks);
    int getAttackTicks();
}
