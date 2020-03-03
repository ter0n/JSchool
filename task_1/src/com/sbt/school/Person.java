package com.sbt.school;

public class Person {

    private final boolean man;
    private final String name;
    private Person spouse;

    public Person(boolean man, String name){
        this.man = man;
        this.name = name;
    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce
     *      (sets spouse = null for husband and wife.
     *      Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife,
     *      false otherwise
     */
    public boolean marry(Person person) {
        if(this.man != person.man){
            if((this.spouse != null) && (this.spouse.name == person.name)
                    && (person.spouse != null) && (person.spouse.name == this.name)){
                return false;
            } else {
                if(this.spouse != null){
                    this.divorce();
                }

                if(person.spouse != null){
                    person.divorce();
                }

                this.spouse = person;
                person.spouse = this;
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Sets spouse = null if spouse is not null
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        if(this.spouse != null){
            Person oldSpouse = this.spouse;
            oldSpouse.spouse = null;
            this.spouse = null;
            return true;
        } else {
            return false;
        }
    }

}
