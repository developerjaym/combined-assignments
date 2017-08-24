package com.cooksys.ftd.assignments.collections.model;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchical;

public interface Capitalist extends Hierarchical<Capitalist, FatCat> {

    /**
     * @return the name of the capitalist
     */
    String getName();

    /**
     * @return the salary of the capitalist, in dollars
     */
    int getSalary();
    
    public Capitalist construct(String n, int s, FatCat o);
    
    default Capitalist defCopy()
    {
    	if((FatCat)getParent() !=  null)
    		return construct(getName(), getSalary(), (FatCat) getParent().defCopy());
    	else 
    		return construct(getName(), getSalary(), null);
    }
}
