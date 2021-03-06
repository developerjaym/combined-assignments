package com.cooksys.ftd.assignments.collections.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WageSlave implements Capitalist {

	private String name;
	private int salary;
	private FatCat owner;
	
    public WageSlave(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
     
    
    public WageSlave(String name, int salary, FatCat owner) {
        this(name, salary);
        this.owner = owner;
    }

    /**
     * @return the name of the capitalist
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the salary of the capitalist, in dollars
     */
    @Override
    public int getSalary() {
        return salary;
    }

    /**
     * @return true if this element has a parent, or false otherwise
     */
    @Override
    public boolean hasParent() {
        if(owner != null)
        	return true;
        return false;
    }

    /**
     * @return the parent of this element, or null if this represents the top of a hierarchy
     */
    @Override
    public FatCat getParent() {
        //return owner;//does this work?
        
        if(hasParent())
        	return owner;
        else
        	return null;
    }
    
    @Override
    public String toString()
    {
    	StringBuilder sb = new StringBuilder("\nName: " + getName());
    	sb.append("\n");
    	sb.append("   Salary: " + getSalary());
    	sb.append("\n");
    	if(getParent() != null)
    		sb.append("   Parent: " + getParent().getName());
    	else
    		sb.append("   Parent: no parent");
    	sb.append("\n   " + getClass().getSimpleName());
    	return sb.toString();
    }

    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + salary;
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WageSlave other = (WageSlave) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}


	@Override
	public Capitalist construct(String n, int s, FatCat o) {
		return new WageSlave(n, s, o);
	}
    
}
