package sn.thecells.entity;

import java.util.ArrayList;
import java.util.List;

import sn.thecells.generic.Drawable;

public abstract class Entity implements Drawable {

	public final String label;
	public final String propFile;
	public final int propIndex;

	public Entity(String label, String propFile, int propIndex) {
		super();
		this.label = label;
		this.propFile = propFile;
		this.propIndex = propIndex;
	}

	@Override
	public String getPropFile() {
		return propFile;
	}

	@Override
	public int getPropIndex() {
		return propIndex;
	}
	public static Entity extractAnyEntity(List<? extends Entity> ss) {
    	int index = (int)Math.floor(Math.random() * ss.size());
    	return ss.remove(index);
    }
    public static List<Entity> extractSomeEntities(List<? extends Entity> entities, int count){
    	List<Entity> result = new ArrayList<Entity>();
    	for (int i = 0; i < count && !entities.isEmpty(); i++)
    		result.add(extractAnyEntity(entities));
    	return result;
    }
}
