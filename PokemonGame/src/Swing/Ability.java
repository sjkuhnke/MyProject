package Swing;

public enum Ability {

	ADAPTABILITY(""),
	ANGER_POINT(""),
	ANTICIPATION(""),
	BERZERK(""),
	BIG_PECKS(""),
	BLAZE(""),
	CHLOROPHYL(""),
	CLEAR_BODY(""),
	COMPETITIVE(""),
	COMPOUND_EYES(""),
	CONTRARY(""),
	DEFIANT(""),
	DRIZZLE(""),
	DROUGHT(""),
	EARLY_BIRD(""),
	ELECTRIC_SURGE(""),
	FALSE_ILLUMINATION(""),
	FILTER(""),
	FLAME_BODY(""),
	FLASH_FIRE(""),
	FLUFFY(""),
	FRIENDLY_GHOST(""),
	GALACTIC_AURA(""),
	GLACIER_AURA(""),
	GRASSY_SURGE(""),
	GUTS(""),
	HUGE_POWER(""),
	HYPER_CUTTER(""),
	ICE_BODY(""),
	ICY_SCALES(""),
	INNER_FOCUS(""),
	INSECT_FEEDER(""),
	INTIMIDATE(""),
	IRON_BARBS(""),
	IRON_FIST(""),
	JUSTIFIED(""),
	KEEN_EYE(""),
	LEAF_GUARD(""),
	LEVITATE(""),
	LIGHTNING_ROD(""),
	MAGIC_GUARD(""),
	MIRROR_ARMOR(""),
	MOTOR_DRIVE(""),
	MOUTHWATER(""),
	MOXIE(""),
	MULTISCALE(""),
	NATURAL_CURE(""),
	NO_GUARD(""),
	NORMALIZE(""),
	OVERGROW(""),
	POISON_POINT(""),
	PRANKSTER(""),
	PROTEAN(""),
	PSYCHIC_AURA(""),
	PSYCHIC_SURGE(""),
	RAIN_DISH(""),
	RATTLED(""),
	RECKLESS(""),
	REGENERATOR(""),
	ROCK_HEAD(""),
	ROUGH_SKIN(""),
	SAND_FORCE(""),
	SAND_RUSH(""),
	SAND_STREAM(""),
	SAND_VEIL("When SANDSTORM is active, this Pokemon is evasive."),
	SAP_SIPPER(""),
	SERENE_GRACE(""),
	SHED_SKIN(""),
	SHEER_FORCE(""),
	SHIELD_DUST(""),
	SIMPLE(""),
	SLUSH_RUSH(""),
	SNIPER(""),
	SNOW_CLOAK("When SNOW is active, this Pokemon is evasive."),
	SNOW_WARNING(""),
	SOLAR_POWER(""),
	SOLID_ROCK(""),
	SPARKLY_SURGE(""),
	SPEED_BOOST(""),
	STATIC(""),
	STEALTHY_PREDATOR(""),
	STEELWORKER(""),
	STRONG_JAW(""),
	STURDY(""),
	SUPER_LUCK(""),
	SWAG(""),
	SWARM(""),
	SWIFT_SWIM(""),
	SYNCHRONIZE(""),
	TECHNICIAN(""),
	THICK_FAT(""),
	TORRENT(""),
	TOUGH_CLAWS(""),
	TYPE_MASTER(""),
	UNAWARE(""),
	UNERODIBLE(""),
	VOLT_ABSORB(""),
	WATER_ABSORB(""),
	WATER_VEIL(""),
	WONDER_GUARD("");

	Ability(String string) {
		desc = string;
	}
	
	@Override
	public String toString() {
		String name = super.toString();
	    name = name.toLowerCase().replace('_', ' ');
	    String[] words = name.split(" ");
	    StringBuilder sb = new StringBuilder();
	    for (String word : words) {
	        sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
	    }
	    return sb.toString().trim();
	}
	
	public String desc;
}
