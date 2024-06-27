package org.puggu.magicandskills.item.wand;

public enum WandType {
    FIRE(){
        @Override
        void handleSpell() {

        }
    },
    LIGHTNING(){
        @Override
        void handleSpell() {

        }
    },
    WATER(){
        @Override
        void handleSpell() {

        }
    };


    abstract void handleSpell();
}
