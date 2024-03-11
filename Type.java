package Pokemon_Game;

public enum Type {
    // Constants
    Normal(new String[]{},
    new String[]{}),
    Fire(new String[]{"Grass", "Bug"},
    new String[]{"Fire", "Water"}),
    Grass(new String[]{"Water"},
    new String[]{"Fire", "Grass", "Bug", "Poison", "Flying"}),
    Water(new String[]{"Fire"},
    new String[]{"Water", "Grass"}),
    Bug(new String[]{"Grass"},
    new String[]{"Fire"}),
    Poison(new String[]{"Grass"},
    new String[]{"Poison"}),
    Flying(new String[]{"Grass", "Bug"},
    new String[]{"Electric"}),
    Electric(new String[]{"Water", "Flying"},
    new String[]{"Grass", "Electric"});

    private final String[] superEffective, notVeryEffective;

    // Constructor
    Type(String[] se, String[] ne) {
        superEffective = se;
        notVeryEffective = ne;
    }

    // Getters

    public String[] getSuperEffective() {
        return superEffective;
    }

    public String[] getNotVeryEffective() {
        return notVeryEffective;
    }

    // Check for relation between types
    public boolean checkEffectTypes(String[] types, Type t) {
        if(types.length == 0) {
            return false;
        }
        for(String type : types) {
            if(type.equals(t + "")) {
                return true;
            }
        }
        return false;
    }

    // return true if Type is Super Effective against another type
    public boolean isSuperEffective(Type t) {
        return checkEffectTypes(superEffective, t);
    }

    // return true if Type is Not Very Effective against another type
    public boolean isNotVeryEffective(Type t) {
        return checkEffectTypes(notVeryEffective, t);
    }
}
