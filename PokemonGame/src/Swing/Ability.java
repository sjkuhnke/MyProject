package Swing;

public enum Ability {

	ADAPTABILITY("Powers up moves of the same type."),
	ANGER_POINT("Maxes Attack after taking a critical hit."),
	ANTICIPATION(""), // TODO
	BERZERK(""), // TODO
	INSOMNIA("Prevents the Pokemon from falling asleep."),
	BLAZE(""), // TODO
	CHLOROPHYLL("Boosts the Pokemon's Speed stat in SUNSHINE."),
	CLEAR_BODY("Prevents other Pokemon from lowering its stats."),
	COMPETITIVE("Boosts the Pokemon's Special Attack stat when its stats are lowered."),
	COMPOUND_EYES("The Pokemon's accuracy is boosted."),
	CONTRARY("Makes stat changes have an opposite effect."),
	DEFIANT("Boosts the Pokemon's Attack stat when its stats are lowered."),
	DRIZZLE("The Pokemon makes it RAIN when it enters a battle."),
	DROUGHT("Turns the sunlight HARSH when it enters a battle."),
	EARLY_BIRD("The Pokemon awakens quickly from sleep."),
	ELECTRIC_SURGE("Turns the ground into ELECTRIC TERRAIN when the Pokemon enters a battle."),
	FALSE_ILLUMINATION(""), // TODO
	FILTER("Reduces damage from supereffective attacks."),
	FLAME_BODY(""), // TODO
	FLASH_FIRE("It powers up FIRE moves if it's hit by one."),
	FLUFFY("Halves the damage taken from moves that make direct contact, but doubles that of FIRE moves."),
	FRIENDLY_GHOST(""), // TODO
	GALACTIC_AURA(""), // TODO
	GLACIER_AURA(""), // TODO
	GRASSY_SURGE("Turns the ground into GRASSY TERRAIN when the Pokemon enters a battle."),
	GRAVITATIONAL_PULL(""), // TODO
	GUTS("Boosts the Attack stat if the Pokemon has a status condition."),
	HUGE_POWER("Doubles the Pokemon's Attack stat."),
	HYPER_CUTTER(""), // TODO
	ICE_BODY(""), // TODO
	ICY_SCALES("The Pokemon is protected by ice scales, which halve the damage taken from special moves."),
	INNER_FOCUS("The Pokemon is protected from flinching."),
	INSECT_FEEDER("Restores HP if hit by a BUG move."),
	INTIMIDATE("Lowers the opposing Pokemon's Attack stat."),
	IRON_BARBS(""), // TODO
	IRON_FIST("Boosts the power of punching moves."),
	JUSTIFIED("Boosts the Attack stat when it's hit by a DARK move."),
	KEEN_EYE("Prevents the Pokemon from losing accuracy."),
	LEVITATE(""), // TODO
	LIGHTNING_ROD("Raises Special Attack if hit by an ELECTRIC move."),
	MAGIC_GUARD("The Pokemon only takes damage from attacks."),
	MIRROR_ARMOR("Bounces back only the stat-lowering effects that the Pokemon receives."),
	MOTOR_DRIVE("Raises Speed if hit by an ELECTRIC move."),
	MOUTHWATER("Prevents the foe from using any non-damaging moves."),
	MOXIE("Boosts the Attack stat after knocking out any Pok�mon."),
	MULTISCALE("Reduces damage the Pok�mon takes when its HP is full."),
	NATURAL_CURE("All status conditions heal when the Pokemon switches out."),
	NO_GUARD("Ensures the Pokemon and its foe's attacks land."),
	NORMALIZE("All the Pokemon's moves become Normal type. The power of those moves is boosted a little."),
	OVERGROW(""), // TODO
	POISON_POINT(""), // TODO
	PRANKSTER("Gives priority to a status move."),
	PROTEAN(""), // TODO
	PSYCHIC_AURA(""), // TODO
	PSYCHIC_SURGE("Turns the ground into PSYCHIC TERRAIN when the Pokemon enters a battle."),
	RAIN_DISH(""), // TODO
	RATTLED("DARK, GHOST, and BUG moves scare the Pokemon and boost its Speed stat."),
	REGENERATOR("Restores a little HP when withdrawn from battle."),
	ROCK_HEAD("Prevents recoil damage."),
	ROUGH_SKIN(""), // TODO
	SAND_FORCE(""), // TODO
	SAND_RUSH("Boosts the Pokemon's Speed stat in a SANDSTORM."),
	SAND_STREAM(""), // TODO
	SAND_VEIL("When SANDSTORM is active, this Pokemon is evasive."),
	SAP_SIPPER("Raises Attack if hit by an GRASS move."),
	SERENE_GRACE("Boosts the likelihood of additional effects occurring when attacking."),
	SHED_SKIN(""), // TODO
	SHEER_FORCE("Removes additional effects to increase the power of moves when attacking."),
	SHIELD_DUST("Blocks the added effects of attacks taken."),
	SIMPLE("The stat changes the Pokemon receives are doubled."),
	SLUSH_RUSH("Boosts the Pokemon's Speed stat in SNOW."),
	SNIPER("Powers up moves if they become critical hits."),
	SNOW_CLOAK("When SNOW is active, this Pokemon is evasive."), // TODO
	SNOW_WARNING("The Pokemon makes it SNOW when it enters a battle."),
	SOLAR_POWER(""), // TODO
	SOLID_ROCK("Reduces damage from supereffective attacks."),
	SPARKLY_SURGE("Turns the ground into SPARKLY TERRAIN when the Pokemon enters a battle."),
	SPEED_BOOST(""), // TODO
	STATIC(""), // TODO
	STEALTHY_PREDATOR("Gives priority to the first move when this Pokemon enters battle."),
	STEELWORKER("Powers up STEEL moves."),
	STRONG_JAW("The Pokemon's strong jaw boosts the power of its biting moves."),
	STURDY(""), // TODO
	SUPER_LUCK("Heightens the critical-hit ratios of moves."),
	SWARM(""), // TODO
	SWIFT_SWIM("Boosts the Pokemon's Speed stat in RAIN."),
	SYNCHRONIZE(""), // TODO
	TECHNICIAN("Powers up the Pokemon's weaker moves."),
	THICK_FAT("Boosts resistance to FIRE and ICE moves."),
	TORRENT(""), // TODO
	TOUGH_CLAWS(""), // TODO
	TYPE_MASTER("Grants STAB on all moves."),
	UNAWARE("Ignores the opposing Pokemon's stat changes."),
	UNERODIBLE("Reduces damage from GRASS, WATER, and GROUND attacks."),
	VOLT_ABSORB("Restores HP if hit by a ELECTRIC move."),
	WATER_ABSORB("Restores HP if hit by a WATER move."),
	WATER_VEIL("Prevents the Pokemon from getting a burn."),
	WONDER_GUARD(""), // TODO
	NULL("");

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
