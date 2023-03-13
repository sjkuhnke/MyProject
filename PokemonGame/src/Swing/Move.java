package Swing;

public enum Move {
	ABSORB(20,100,0,0,1,false,PType.GRASS),
	ACID(40,100,10,0,1,false,PType.POISON),
	AEROBLAST(100,95,0,1,1,false,PType.FLYING),
	AGILITY(0,1000,0,0,2,false,PType.FLYING),
	AIR_CUTTER(55,95,0,1,1,false,PType.FLYING),
	AIR_SLASH(75,95,30,0,1,false,PType.FLYING),
	ANCIENT_POWER(60,100,10,0,1,false,PType.ROCK),
	AQUA_RING(0,1000,0,0,2,false,PType.WATER),
	AQUA_TAIL(90,90,0,0,0,false,PType.WATER),
	ASSURANCE(50,100,0,0,0,false,PType.DARK),
	ASTONISH(30,100,30,0,0,false,PType.GHOST),
	AURA_SPHERE(90,1000,0,0,1,false,PType.FIGHTING),
	AUTOMOTIZE(0,1000,0,0,2,false,PType.STEEL),
	AUTO_SHOT(0,1000,0,0,2,false,PType.STEEL),
	BAWL(0,100,0,0,2,false,PType.DARK),
	BEAT_UP(60,100,50,0,0,false,PType.DARK),
	BIG_BULLET(70,90,30,0,0,false,PType.STEEL),
	BITE(60,100,30,0,0,false,PType.DARK),
	BLACK_DUST(0,100,0,0,2,false,PType.FIRE),
	BLACK_HOLE(90,90,100,0,1,false,PType.DARK),
	BLAST_BURN(150,90,0,0,1,false,PType.FIRE), // recharge
	BLAST_FLAME(100,100,100,0,1,false,PType.FIRE),
	BLAZING_SWORD(90,90,50,0,0,false,PType.FIRE),
	BLIND(0,100,0,0,2,false,PType.NORMAL),
	BLUE_FLARE(150,90,50,0,1,false,PType.FIRE),
	BODY_SLAM(85,100,30,0,0,false,PType.NORMAL),
	BOLT_STRIKE(150,90,50,0,1,false,PType.ELECTRIC),
	BOULDER_CRUSH(85,80,50,0,0,false,PType.ROCK),
	BOULDER_SLAM(70,100,0,0,0,false,PType.ROCK),
	BOUNCE(85,85,30,0,0,false,PType.FLYING),
	BRANCH_WHACK(50,95,0,0,0,false,PType.ROCK),
	BRAVE_BIRD(120,100,0,0,0,false,PType.FLYING), // recoil
	BRICK_BREAK(75,100,0,0,0,false,PType.FIGHTING),
	BRINE(-1,100,0,0,1,false,PType.WATER),
	BUBBLE(20,100,0,0,1,false,PType.WATER),
	BUBBLEBEAM(65,100,10,0,1,false,PType.WATER),
	BUG_BITE(60,100,0,0,0,false,PType.BUG),
	BUG_BUZZ(90,100,0,0,1,false,PType.BUG),
	BULLET_PUNCH(40,100,0,0,0,true,PType.STEEL),
	BUZZ(0,100,0,0,2,false,PType.BUG),
	CHARGE(0,1000,0,0,2,false,PType.ELECTRIC),
	CHARM(0,100,0,0,2,false,PType.NORMAL),
	CHOMP(70,100,30,0,0,false,PType.DARK),
	CLOSE_COMBAT(120,100,100,0,0,false,PType.FIGHTING),
	COMET_CRASH(-1,90,0,0,0,false,PType.MAGIC),
	COMET_PUNCH(-1,85,0,0,0,false,PType.MAGIC),
	CONFUSE_RAY(0,100,0,0,2,false,PType.GHOST),
	CONFUSION(50,100,10,0,1,false,PType.MAGIC),
	CONSTRICT(10,100,50,0,0,false,PType.NORMAL),
	CROSS_POISON(70,100,30,1,0,false,PType.POISON),
	CRUNCH(80,100,30,0,0,false,PType.DARK),
	CURSE(0,100,0,0,2,false,PType.GHOST),
	CUT(50,95,0,0,0,false,PType.NORMAL),
	DARK_PULSE(80,100,30,0,1,false,PType.DARK),
	DARK_VOID(0,80,0,0,2,false,PType.DARK),
	DEFENSE_CURL(0,1000,0,0,2,false,PType.NORMAL),
	DESTINY_BOND(0,1000,0,0,2,true,PType.GHOST),
	DISAPPEAR(0,1000,50,0,2,false,PType.GHOST),
	DISCHARGE(80,100,30,0,1,false,PType.ELECTRIC),
	DIVE(80,90,0,0,0,false,PType.WATER),
	DOUBLE_BLAST(-1,60,30,0,1,false,PType.NORMAL),
	DOUBLE_HIT(-1,95,0,0,0,false,PType.NORMAL),
	DOUBLE_JET(-1,85,0,0,0,false,PType.WATER),
	DOUBLE_KICK(-1,85,0,0,0,false,PType.FIGHTING),
	DOUBLE_PUNCH(-1,85,0,0,0,false,PType.FIGHTING),
	DOUBLE_SLAP(-1,95,0,0,0,false,PType.NORMAL),
	DOUBLE_SLICE(-1,80,15,0,0,false,PType.STEEL),
	DOUBLE_TEAM(0,1000,0,0,2,false,PType.NORMAL),
	DOUBLE_EDGE(120,100,0,0,0,false,PType.NORMAL), // recoil
	DRACO_METEOR(140,100,100,0,1,false,PType.DRAGON),
	DRAGON_CLAW(80,100,0,1,0,false,PType.DRAGON),
	DRAGON_DANCE(0,1000,0,0,2,false,PType.DRAGON),
	DRAGON_PULSE(90,100,0,0,1,false,PType.DRAGON),
	DRAGON_RAGE(0,100,0,0,1,false,PType.DRAGON),
	DRAGON_RUSH(100,75,30,0,0,false,PType.DRAGON),
	DRAGON_BREATH(60,100,30,0,1,false,PType.DRAGON),
	DREAM_EATER(100,100,0,0,1,false,PType.DARK),
	DRILL_PECK(80,100,0,1,0,false,PType.FLYING),
	DRILL_RUN(80,95,0,1,0,false,PType.GROUND),
	DUAL_STAB(-1,80,15,0,0,false,PType.STEEL),
	EARTH_POWER(90,100,10,0,1,false,PType.GROUND),
	EARTHQUAKE(100,100,0,0,0,false,PType.GROUND),
	ELECTROBALL(-1,100,0,0,1,false,PType.ELECTRIC),
	ELECTROEXPLOSION(300,100,0,0,1,false,PType.ELECTRIC),
	EMBER(40,100,10,0,1,false,PType.FIRE),
	ERUPTION(-1,100,0,0,1,false,PType.FIRE),
	EXPLOSION(250,100,0,0,0,false,PType.NORMAL),
	EXTRAORDINARY(-1,1000,0,0,1,false,PType.MAGIC),
	EXTREME_SPEED(70,100,0,0,0,false,PType.FLYING),
	FAINT_ATTACK(60,1000,0,0,0,false,PType.DARK),
	FALSE_SWIPE(40,100,0,0,0,false,PType.BUG),
	FIREBALL(-1,100,10,0,1,false,PType.FIRE),
	FIRE_BLAST(120,85,10,0,1,false,PType.FIRE),
	FIRE_CHARGE(75,90,10,0,0,false,PType.FIRE),
	FIRE_DASH(0,100,100,0,0,false,PType.FIRE),
	FIRE_FANG(65,95,10,0,0,false,PType.FIRE),
	FIRE_PUNCH(75,100,10,0,0,false,PType.FIRE),
	FIRE_SPIN(35,85,100,0,1,false,PType.FIRE),
	FIRE_TAIL(85,90,10,0,0,false,PType.FIRE),
	FLAIL(-1,100,0,0,0,false,PType.NORMAL),
	FLAME_BURST(110,100,100,0,1,false,PType.FIRE),
	FLAME_WHEEL(60,100,10,0,0,false,PType.FIRE),
	FLAMETHROWER(90,100,10,0,1,false,PType.FIRE),
	FLARE_BLITZ(120,100,10,0,0,false,PType.FIRE), // recoil
	FLASH(0,100,0,0,2,false,PType.NORMAL),
	FLASH_CANNON(80,100,50,0,1,false,PType.STEEL),
	FLY(90,100,0,0,0,false,PType.FLYING),
	FOCUS_PUNCH(150,100,0,0,0,false,PType.FIGHTING),
	FORESIGHT(0,1000,0,0,2,false,PType.MAGIC),
	FRENZY_PLANT(150,90,0,0,1,false,PType.GRASS), // recharge
	FRUSTERATION(104,100,0,0,0,false,PType.NORMAL),
	FURY_ATTACK(-1,85,0,0,0,false,PType.NORMAL),
	FURY_CUTTER(-1,95,0,0,0,false,PType.BUG),
	FURY_SWIPES(-1,80,0,0,0,false,PType.NORMAL),
	GALAXY_ATTACK(115,90,30,0,0,false,PType.MAGIC),
	GIGA_DRAIN(75,100,0,0,1,false,PType.GRASS),
	GIGA_HIT(110,75,50,0,0,false,PType.FIGHTING),
	GIGA_IMPACT(150,90,0,0,0,false,PType.NORMAL), // recharge
	GLARE(0,100,0,0,2,false,PType.DARK),
	GRASS_KNOT(80,100,0,0,1,false,PType.GRASS),
	GRASS_PUNCH(80,100,0,0,0,false,PType.GRASS),
	GROWL(0,100,0,0,2,false,PType.NORMAL),
	GROWTH(0,1000,0,0,2,false,PType.GRASS),
	GUNK_SHOT(120,70,30,0,1,false,PType.POISON),
	GUNSHOT(70,60,0,2,0,false,PType.STEEL),
	GUST(40,100,0,0,1,false,PType.FLYING),
	GYRO_BALL(-1,100,0,0,0,false,PType.STEEL),
	HAMMER_ARM(100,90,100,0,0,false,PType.FIGHTING),
	HARDEN(0,1000,0,0,2,false,PType.NORMAL),
	HAZE(0,1000,0,0,2,false,PType.GHOST),
	HEAD_SMASH(150,80,0,0,0,false,PType.ROCK), // recoil
	HEADBUTT(70,100,30,0,0,false,PType.NORMAL),
	HEAT_WAVE(100,90,10,0,1,false,PType.FIRE),
	HI_JUMP_KICK(130,90,0,0,0,false,PType.FIGHTING),
	HORN_ATTACK(65,100,0,0,0,false,PType.NORMAL),
	HORN_DRILL(0,30,0,0,0,false,PType.NORMAL),
	HYDRO_CANNON(150,90,0,0,1,false,PType.WATER), // recharge
	HYDRO_PUMP(120,80,0,0,1,false,PType.WATER),
	HYPER_BEAM(150,90,0,0,1,false,PType.NORMAL), // recharge
	HYPER_FANG(80,90,10,0,0,false,PType.NORMAL),
	HYPNOSIS(0,60,0,0,2,false,PType.GHOST),
	IGNITE(0,75,0,0,2,false,PType.FIRE),
	INJECT(55,100,100,0,0,false,PType.BUG),
	IRON_DEFENSE(0,1000,0,0,2,false,PType.STEEL),
	IRON_HEAD(80,100,30,0,0,false,PType.STEEL),
	IRON_TAIL(100,75,30,0,0,false,PType.STEEL),
	KARATE_CHOP(50,100,0,1,0,true,PType.FIGHTING),
	LAVA_PLUME(80,100,30,0,1,false,PType.FIRE),
	LEAF_BALL(75,95,0,0,1,false,PType.GRASS),
	LEAF_BLADE(90,100,0,1,0,false,PType.GRASS),
	LEAF_GUST(50,100,0,0,1,false,PType.GRASS),
	LEAF_KOBE(75,90,100,0,0,false,PType.GRASS),
	LEAF_PULSE(85,75,100,0,1,false,PType.GRASS),
	LEAF_SLAP(30,100,0,0,0,false,PType.GRASS),
	LEAF_STORM(140,90,100,0,1,false,PType.GRASS),
	LEAF_TORNADO(65,90,50,0,1,false,PType.GRASS),
	LEECH_LIFE(20,100,0,0,0,false,PType.BUG),
	LEECH_SEED(0,90,0,0,2,false,PType.GRASS),
	LEER(0,100,0,0,2,false,PType.NORMAL),
	LICK(20,100,30,0,0,false,PType.GHOST),
	LIGHTNING_HEADBUTT(90,95,30,0,0,false,PType.ELECTRIC), // recoil
	LOCK_ON(0,1000,0,0,2,false,PType.STEEL),
	LOW_KICK(60,100,100,0,0,false,PType.FIGHTING),
	MACH_PUNCH(40,100,0,0,0,true,PType.FIGHTING),
	MACHETE_STAB(75,80,100,0,0,false,PType.STEEL),
	MAGIC_BLAST(30,100,0,0,1,false,PType.MAGIC),
	MAGIC_CRASH(150,90,100,0,0,false,PType.MAGIC), // recharge
	MAGIC_FANG(70,95,75,0,0,false,PType.MAGIC),
	MAGIC_REFLECT(0,1000,0,0,2,false,PType.MAGIC),
	MAGIC_TOMB(90,100,0,0,0,false,PType.MAGIC),
	MAGICAL_LEAF(60,1000,0,0,1,false,PType.GRASS),
	MAGNET_RISE(0,1000,0,0,2,false,PType.STEEL),
	MAGNITUDE(-1,100,0,0,0,false,PType.GROUND),
	MEGA_DRAIN(40,100,0,0,1,false,PType.GRASS),
	MEGA_KICK(70,100,60,0,0,false,PType.FIGHTING),
	MEGA_PUNCH(70,100,60,0,0,false,PType.FIGHTING),
	MEGA_SWORD(70,100,60,0,0,false,PType.STEEL),
	METAL_SOUND(0,100,0,0,2,false,PType.STEEL),
	MINIMIZE(0,1000,0,0,2,false,PType.GHOST),
	MIRROR_MOVE(0,1000,0,0,1,false,PType.FLYING),
	MOONLIGHT(0,1000,0,0,2,false,PType.MAGIC),
	MUD_BOMB(65,85,30,0,1,false,PType.GROUND),
	MUD_SLAP(20,100,100,0,1,false,PType.GROUND),
	MUD_SPORT(0,1000,0,0,2,false,PType.GROUND),
	NEEDLE_SPRAY(55,95,10,0,0,false,PType.POISON),
	NIBBLE(10,100,0,0,0,false,PType.NORMAL),
	NIGHT_SHADE(0,100,0,0,1,false,PType.GHOST),
	NIGHT_SLASH(70,100,0,1,0,false,PType.DARK),
	NIGHTMARE(0,100,0,0,2,false,PType.GHOST),
	ODOR_SLEUTH(0,1000,0,0,2,false,PType.NORMAL),
	OUTRAGE(120,100,0,0,0,false,PType.DRAGON),
	OVERHEAT(140,90,100,0,1,false,PType.FIRE),
	PECK(35,100,0,0,0,false,PType.FLYING),
	PERISH_SONG(0,1000,0,0,2,false,PType.GHOST),
	PHASE_SHIFT(0,1000,0,0,2,false,PType.MAGIC),
	POISON_BALL(65,80,100,0,0,false,PType.POISON),
	POISON_FANG(50,100,30,0,0,false,PType.POISON),
	POISON_GAS(0,80,0,0,2,false,PType.POISON),
	POISON_JAB(80,100,30,0,0,false,PType.POISON),
	POISON_POWDER(0,75,0,0,2,false,PType.POISON),
	POISON_PUNCH(75,100,10,0,0,false,PType.POISON),
	POISON_STING(15,100,30,0,0,false,PType.POISON),
	POISONOUS_WATER(95,85,30,0,1,false,PType.POISON),
	POKE(10,100,0,0,0,false,PType.NORMAL),
	POUND(40,100,0,0,0,false,PType.NORMAL),
	SHELL_SMASH(0,1000,0,0,2,true,PType.NORMAL),
	PSYCHO_BLADE(70,100,0,1,0,false,PType.MAGIC),
	PUNCH(40,90,0,0,0,false,PType.FIGHTING),
	PURSUIT(40,100,0,0,0,false,PType.DARK),
	QUICK_ATTACK(0,100,0,0,0,true,PType.NORMAL),
	RAGE(-1,100,0,0,0,false,PType.NORMAL),
	RAPID_SPIN(20,100,100,0,0,false,PType.NORMAL),
	RAZOR_LEAF(55,95,0,1,0,false,PType.GRASS),
	REBOOT(0,1000,0,0,2,false,PType.STEEL),
	REVENGE(-1,100,0,0,0,false,PType.FIGHTING),
	ROCK_BLADE(80,100,0,1,0,false,PType.ROCK),
	ROCK_BLAST(-1,90,0,0,0,false,PType.ROCK),
	ROCK_SMASH(40,100,50,0,0,false,PType.FIGHTING),
	ROCK_THROW(50,90,0,0,0,false,PType.ROCK),
	ROCK_TOMB(60,80,100,0,0,false,PType.ROCK),
	ROCKET(120,75,50,0,0,false,PType.STEEL),
	ROLLOUT(-1,90,0,0,0,false,PType.ROCK),
	ROOST(0,1000,0,0,2,false,PType.FLYING),
	ROOT_CRUSH(105,90,100,0,0,false,PType.ROCK),
	ROOT_KICK(60,95,0,0,0,false,PType.ROCK),
	SAND_ATTACK(0,100,0,0,2,false,PType.GROUND),
	SCARY_FACE(0,100,0,0,2,false,PType.NORMAL),
	SCRATCH(40,100,0,0,0,false,PType.NORMAL),
	SCREECH(0,85,0,0,2,false,PType.NORMAL),
	SEISMIC_TOSS(0,100,0,0,0,false,PType.FIGHTING),
	SELF_DESTRUCT(200,100,0,0,0,false,PType.NORMAL),
	SHADOW_BALL(80,100,30,0,1,false,PType.GHOST),
	SHADOW_SNEAK(40,100,0,0,0,true,PType.GHOST),
	SHELL_BASH(70,100,0,1,0,false,PType.NORMAL), // recoil
	SHOCK(15,100,100,0,1,false,PType.ELECTRIC),
	SHOCK_WAVE(60,1000,0,0,1,false,PType.ELECTRIC),
	SHURIKEN(75,85,50,0,0,false,PType.STEEL),
	SKULL_BASH(100,100,100,0,0,false,PType.NORMAL), // charge
	SKY_ATTACK(140,90,30,1,0,false,PType.FLYING), // charge
	SKY_UPPERCUT(85,90,0,0,0,false,PType.FIGHTING),
	SLAM(80,75,0,0,0,false,PType.NORMAL),
	SLAP(20,100,0,0,0,false,PType.NORMAL),
	SLASH(70,100,0,1,0,false,PType.NORMAL),
	SLEEP_POWDER(0,75,0,0,2,false,PType.GRASS),
	SLUDGE(65,100,30,0,1,false,PType.POISON),
	SLUDGE_BOMB(90,100,30,0,1,false,PType.POISON),
	SMASH(70,90,0,0,0,false,PType.NORMAL),
	SMOG(20,70,50,0,1,false,PType.POISON),
	SMOKESCREEN(0,100,0,0,2,false,PType.NORMAL),
	SOLAR_BEAM(120,100,0,0,1,false,PType.GRASS), // charge
	SPARK(65,100,30,0,0,false,PType.ELECTRIC),
	SPIKE_JAB(55,80,100,0,0,false,PType.POISON),
	SPIKE_SHOT(20,100,0,0,0,false,PType.POISON),
	SPIKE_SLAM(65,90,0,0,0,false,PType.NORMAL),
	STAR_STORM(110,85,0,0,1,false,PType.MAGIC),
	STARE(0,100,0,0,2,false,PType.NORMAL),
	STING(55,100,100,0,0,false,PType.BUG),
	STOMP(65,100,30,0,0,false,PType.NORMAL),
	STONE_EDGE(100,80,0,1,0,false,PType.ROCK),
	STRENGTH(80,100,0,0,0,false,PType.NORMAL),
	STRING_SHOT(0,100,0,0,2,false,PType.BUG),
	STRONG_ARM(90,85,30,0,0,false,PType.FIGHTING),
	SUCKER_PUNCH(80,100,0,0,0,true,PType.DARK),
	SUPER_CHARGE(90,50,100,0,0,false,PType.ELECTRIC), // recoil
	SUPER_FANG(0,100,0,0,0,false,PType.NORMAL),
	SUPERPOWER(120,100,100,0,0,false,PType.FIGHTING),
	SUPERSONIC(0,55,0,0,2,false,PType.NORMAL),
	SURF(95,100,0,0,1,false,PType.WATER),
	SWAGGER(0,85,0,0,2,false,PType.NORMAL),
	SWEEP_KICK(60,95,100,0,0,false,PType.FIGHTING),
	SWIFT(60,1000,0,0,1,false,PType.MAGIC),
	SWORD_SLASH(75,90,0,1,0,false,PType.STEEL),
	SWORD_SLICE(65,85,0,1,0,false,PType.STEEL),
	SWORD_SPIN(50,95,100,0,0,false,PType.STEEL),
	SWORD_STAB(95,60,100,0,0,false,PType.STEEL),
	SYNTHESIS(0,1000,0,0,2,false,PType.GRASS),
	TACKLE(50,100,0,0,0,false,PType.NORMAL),
	TAIL_WHACK(90,85,0,0,0,false,PType.NORMAL),
	TAIL_WHIP(0,100,0,0,2,false,PType.NORMAL),
	TAKE_DOWN(90,85,0,0,0,false,PType.NORMAL), // recoil
	TAKE_OVER(0,100,0,0,2,false,PType.GHOST),
	THUNDER(120,70,30,0,1,false,PType.ELECTRIC),
	THUNDERBOLT(95,100,10,0,1,false,PType.ELECTRIC),
	THUNDER_FANG(65,95,10,0,0,false,PType.ELECTRIC),
	THUNDER_KICK(80,90,10,0,0,false,PType.ELECTRIC),
	THUNDER_PUNCH(75,100,10,0,0,false,PType.ELECTRIC),
	THUNDERSHOCK(40,100,10,0,1,false,PType.ELECTRIC),
	THUNDER_WAVE(0,100,0,0,2,false,PType.ELECTRIC),
	TIDAL_WAVE(-1,100,0,0,1,false,PType.WATER),
	TORNADO_SPIN(60,95,100,0,0,false,PType.FIGHTING),
	TOXIC(0,100,0,0,2,false,PType.POISON),
	TWISTER(40,100,10,0,1,false,PType.DRAGON),
	FIRST_IMPRESSION(90,100,0,0,0,true,PType.BUG),
	VINE_WHIP(35,100,0,0,0,false,PType.GRASS),
	VITAL_THROW(60,1000,0,0,0,false,PType.FIGHTING),
	VOLT_TACKLE(120,100,10,0,0,false,PType.ELECTRIC), // recoil
	WAKE_UP_SLAP(-1,100,0,0,0,false,PType.FIGHTING),
	WATER_GUN(40,100,0,0,1,false,PType.WATER),
	WATER_JET(50,100,0,0,0,true,PType.WATER),
	WATER_PULSE(60,100,30,0,1,false,PType.WATER),
	WATERFALL(80,100,10,0,0,false,PType.WATER),
	WHIP_SMASH(120,100,0,0,0,false,PType.NORMAL),
	WHIRLPOOL(35,85,100,0,1,false,PType.WATER),
	WILL_O_WISP(0,80,0,0,2,false,PType.FIRE),
	WING_ATTACK(60,100,0,0,0,false,PType.FLYING),
	WOOD_FANG(50,100,50,0,0,false,PType.ROCK),
	WRAP(15,90,100,0,0,false,PType.NORMAL),
	WRING_OUT(-1,100,0,0,0,false,PType.NORMAL),
	X_SCIZZOR(80,100,0,1,0,false,PType.BUG),
	ZAP(20,100,0,0,0,false,PType.ELECTRIC),
	ZEN_HEADBUTT(80,90,30,0,0,false,PType.MAGIC),
	ROCK_SLIDE(75,90,30,0,0,false,PType.ROCK),
	ROCK_POLISH(0,1000,0,0,2,false,PType.ROCK),
	ROCK_WRECKER(150,90,0,0,0,false,PType.ROCK), 
	FAILED_SUCKER(0,100,0,0,0,false,PType.DARK) // recoil
	
	;
	
	Move(int bp, int acc, int sec, int crit, int cat, boolean p, PType type){
		this.basePower = bp;
		this.accuracy = acc;
		this.secondary = sec;
		this.cat = cat;
		this.critChance = crit;
		this.mtype = type;
		this.priority = p;
	}

	public boolean isPhysical() {
		return cat == 0;
	}
	
	public boolean isAttack() {
		return cat != 2;
	}
	
	@Override // implementation
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
	
	public int basePower;
	public int accuracy;
	public int secondary;
	public int cat;
	public int critChance;
	public PType mtype;
	public boolean priority;
	
	public boolean isPriority() {
		return priority;
	}

	public static Move getMove(String moveName) {
    for (Move move : Move.values()) {
        if (move.toString().equalsIgnoreCase(moveName)) {
            return move;
        }
    }
    return null;
}

}
