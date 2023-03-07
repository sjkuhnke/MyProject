package Swing;

public enum Status {
	BURNED,
	PARALYZED,
	ASLEEP,
	POISONED,
	HEALTHY,
	BLEEDING,
	CONFUSED,
	FROZEN,
	CURSED,
	LEECHED,
	NIGHTMARE,
	PERISH;
	
	@Override // implementation
	public String toString() {
		switch(this) {
        case BURNED: 
            return "BRN";
        case PARALYZED: 
            return "PRZ";
		case ASLEEP:
			return "ASL";
		case FROZEN:
			return "FRZ";
		case POISONED:
			return "PSN";
		case HEALTHY:
			return "GOOD";
		case BLEEDING:
			return "BLD";
		case CONFUSED:
			return "CNF";
		default:
			return "HEALTHY";
    }
	}
}
