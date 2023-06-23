package Swing;

public enum Move {
	ABDUCT(0,100,0,0,2,0,PType.GALACTIC,"Abducts the foe and forces their next move to be used on themselves. Can be used once every other turn"),
	ABSORB(20,100,0,0,1,0,PType.GRASS,"Heals 50% of damage dealt to foe"),
	ACCELEROCK(40,100,0,0,0,1,PType.ROCK,"Always goes first"),
	ACID(40,100,10,0,1,0,PType.POISON,"% chance to lower foe's Sp.Def by 1"),
	ACID_ARMOR(0,1000,0,0,2,0,PType.POISON,"Raises user's Defense by 2"),
	ACID_SPRAY(40,100,100,0,1,0,PType.POISON,"% chance to lower foe's Sp.Def by 2"),
	ACROBATICS(90,90,30,0,0,0,PType.FLYING,"% chance of causing foe to flinch"),
	AERIAL_ACE(60,1000,0,0,0,0,PType.FLYING,"This attack always hits"),
	AEROBLAST(100,95,0,1,1,0,PType.FLYING,"Boosted Crit Rate"),
	AGILITY(0,1000,0,0,2,0,PType.FLYING,"Raises user's Speed by 2"),
	AIR_CUTTER(55,95,0,1,1,0,PType.FLYING,"Boosted Crit Rate"),
	AIR_SLASH(75,95,30,0,1,0,PType.FLYING,"% chance of causing foe to flinch"),
	AMNESIA(0,1000,0,0,2,0,PType.PSYCHIC,"Raises user's Sp.Def by 2"),
	ANCIENT_POWER(60,100,10,0,1,0,PType.ROCK,"% chance to raise all of the user's stats by 1"),
	AQUA_JET(40,100,0,0,0,1,PType.WATER,"Always goes first"),
	AQUA_RING(0,1000,0,0,2,0,PType.WATER,"Restores a small amount of HP at the end of every turn"),
	AQUA_TAIL(90,90,0,0,0,0,PType.WATER,"A normal attack"),
	AROMATHERAPY(0,1000,0,0,2,0,PType.GRASS,"Cures team of any status conditions"),
	//ASSURANCE(50,100,0,0,0,0,PType.DARK,"A normal attack"),
	ASTONISH(30,100,30,0,0,0,PType.GHOST,"% chance of causing foe to flinch"),
	AUORA_VEIL(0,1000,0,0,2,0,PType.ICE,"Can only be used in SNOW, reduces both physical and special damage recieved for 5 turns"),
	AURA_SPHERE(90,1000,0,0,1,0,PType.FIGHTING,"This attack always hits"),
	//AUTO_SHOT(0,1000,0,0,2,0,PType.STEEL,"Causes all of user's \"Shooting\" moves to hit twice"),
	AURORA_BEAM(65,100,10,0,1,0,PType.ICE,"% chance to lower foe's Attack by 1"),
	AUTOMOTIZE(0,1000,0,0,2,0,PType.STEEL,"Raises user's Speed by 2"),
	BABY_DOLL_EYES(0,100,0,0,2,1,PType.LIGHT,""), //TODO
	//BAWL(0,100,0,0,2,0,PType.DARK,"Lowers foe's Attack by 2"),
	BEAT_UP(-1,100,0,0,0,0,PType.DARK,"% chance to cause foe to Bleed"), //TODO
	BEEFY_BASH(100,85,50,0,0,-1,PType.FIGHTING,"% chance to paralyze foe, moves last"),
	BELCH(120,100,0,0,1,0,PType.POISON,"Only works on the first turn out"),
	//BIG_BULLET(70,90,30,0,0,0,PType.STEEL,"% chance to Paralyze foe"),
	BIND(20,85,100,0,0,0,PType.NORMAL,""),
	BITE(60,100,30,0,0,0,PType.DARK,"% chance of causing foe to flinch"),
	//BLACK_DUST(0,100,0,0,2,0,PType.FIRE,"Lowers foe's Accuracy by 2"),
	//BLACK_HOLE(90,90,100,0,1,0,PType.DARK,"% chance of lowering foe's Accuracy by 1"),
	BLACK_HOLE_ECLIPSE(140,100,100,0,1,0,PType.GALACTIC,""),
	BLAZE_KICK(85,90,10,1,0,0,PType.FIRE,""),
	BLAST_BURN(150,90,0,0,1,0,PType.FIRE,"User must rest after using this move"), // recharge
	//BLAST_FLAME(100,100,100,0,1,0,PType.FIRE,"% chance to Burn foe"),
	//BLAZING_SWORD(90,90,50,0,0,0,PType.FIRE,"% chance to Burn foe"),
	//BLIND(0,100,0,0,2,0,PType.NORMAL,"Lowers foe's Accuracy by 2"),
	BLIZZARD(110,70,20,0,1,0,PType.ICE,""),
	BLUE_FLARE(130,85,20,0,1,0,PType.FIRE,"% chance to Burn foe"),
	BODY_PRESS(80,100,0,0,0,0,PType.FIGHTING,""),
	BODY_SLAM(85,100,30,0,0,0,PType.NORMAL,"% chance to Paralyze foe"),
	BOLT_STRIKE(130,85,20,0,0,0,PType.ELECTRIC,"% chance to Paralyze foe"),
	//BOULDER_CRUSH(85,80,50,0,0,0,PType.ROCK,"% chance of causing foe to flinch"),
	//BOULDER_SLAM(70,100,0,0,0,0,PType.ROCK,"A normal attack"),
	BOUNCE(85,85,30,0,0,0,PType.FLYING,"% chance to Paralyze foe"),
	//BRANCH_WHACK(50,95,0,0,0,0,PType.ROCK,"A normal attack"),
	BRANCH_POKE(40,100,0,0,0,0,PType.GRASS,""),
	BRAVE_BIRD(120,100,0,0,0,0,PType.FLYING,"User takes 1/3 of damage inflicted"), // recoil
	BRICK_BREAK(75,100,100,0,0,0,PType.FIGHTING,"Breaks Screen effects"), //TODO
	BRINE(-1,100,0,0,1,0,PType.WATER,"Damage is doubled if foe is below 50% HP"), 
	BRUTAL_SWING(60,100,0,0,0,0,PType.DARK,"A normal attack"),
	BUBBLE(20,100,0,0,1,0,PType.WATER,"A normal attack"),
	BUBBLEBEAM(65,100,10,0,1,0,PType.WATER,"% to lower foe's Speed by 1"),
	BUG_BITE(60,100,0,0,0,0,PType.BUG,"A normal attack"),
	BUG_BUZZ(90,100,10,0,1,0,PType.BUG,"% chance to lower foe's Sp.Def by 1"),
	BULK_UP(0,1000,0,0,2,0,PType.FIGHTING,"Raises user's Attack and Defense by 1"),
	BULLDOZE(60,100,100,0,0,0,PType.GROUND,""),
	BULLET_PUNCH(40,100,0,0,0,1,PType.STEEL,"Always goes first"),
	BURN_UP(130,100,100,0,0,0,PType.FIRE,""),
	//BUZZ(0,100,0,0,2,0,PType.BUG,"Confuses foe"),
	CALM_MIND(0,1000,0,0,2,0,PType.MAGIC,"Raises user's Sp.Atk and Sp.Def by 1"),
	CHANNELING_BLOW(0,100,0,0,0,0,PType.FIGHTING,""), // TODO
	CHARGE(0,1000,0,0,2,0,PType.ELECTRIC,"User's next electric-type attack damage is doubled. Raises user's Sp.Def by 1"),
	CHARGE_BEAM(50,90,50,0,1,0,PType.ELECTRIC,""),
	CHARM(0,100,0,0,2,0,PType.LIGHT,"Lowers foe's Attack by 2"),
	CIRCLE_THROW(60,90,0,0,0,-6,PType.FIGHTING,""), // TODO
	//CHOMP(70,100,30,0,0,0,PType.DARK,"% chance to lower foe's Speed by 1"),
	CLOSE_COMBAT(120,100,100,0,0,0,PType.FIGHTING,"Lowers user's Defense and Sp.Def by 1"),
	COIL(0,1000,0,0,2,0,PType.POISON,"Raises user's Atk, Def, and Acc by 1"),
	COMET_CRASH(-1,90,0,0,0,0,PType.MAGIC,"Damage is doubled if user's HP is full"),
	COMET_PUNCH(55,100,0,0,0,0,PType.GALACTIC,"A normal attack"),
	CONFUSE_RAY(0,100,0,0,2,0,PType.GHOST,"Confuses foe"),
	CONFUSION(50,100,10,0,1,0,PType.PSYCHIC,"% chance to Confuse foe"),
	//CONSTRICT(10,100,50,0,0,0,PType.NORMAL,"% chance to lower foe's Speed by 1"),
	CORE_ENFORCER(100,100,10,0,1,0,PType.GALACTIC,""),
	COSMIC_POWER(0,1000,0,0,2,0,PType.GALACTIC,"Raises user's Def and Sp.Def by 1"),
	COTTON_GUARD(0,1000,0,0,2,0,PType.GRASS,"Raises user's Defense by 3"),
	COUNTER(-1,100,0,0,0,-5,PType.FIGHTING,""), // TODO
	CROSS_CHOP(100,80,0,1,0,0,PType.FIGHTING,""),
	CROSS_POISON(70,100,10,1,0,0,PType.POISON,"% chance to Poison foe, boosted Crit rate"),
	CRUNCH(80,100,30,0,0,0,PType.DARK,"% chance to lower foe's Defense by 1"),
	CURSE(0,100,0,0,2,0,PType.GHOST,"User loses half of its total HP. In exchance, foe takes 1/4 of its max HP at the end of every turn"),
	CUT(50,95,0,0,0,0,PType.NORMAL,"A normal attack"),
	DARK_PULSE(80,100,30,0,1,0,PType.DARK,"% chance of causing foe to flinch"),
	DARKEST_LARIAT(85,100,0,0,0,0,PType.DARK,""),
	DAZZLING_GLEAM(80,100,0,0,1,0,PType.LIGHT,""),
	DEEP_SEA_BUBBLE(0,100,0,0,2,0,PType.WATER,""), // TODO
	//DARK_VOID(0,80,0,0,2,0,PType.DARK,"Foe falls asleep"),
	DEFENSE_CURL(0,1000,0,0,2,0,PType.NORMAL,"Raises user's Defense by 1"),
	DEFOG(0,1000,0,0,2,0,PType.FLYING,""),
	DESOLATE_VOID(65,85,50,0,1,0,PType.GALACTIC,""),
	DESTINY_BOND(0,1000,0,0,2,1,PType.GHOST,"Always goes first; can't be used twice in a row. If foe knocks out user the same turn, foe faints as well"),
	//DISAPPEAR(0,1000,50,0,2,0,PType.GHOST,"% chance to Confuse foe; raises user's Evasion by 2"),
	DETECT(0,1000,0,0,2,4,PType.FIGHTING,""), // TODO
	DIAMOND_STORM(100,95,50,0,0,0,PType.FIGHTING,""),
	DIG(80,100,0,0,0,0,PType.GROUND,""),
	DISCHARGE(80,100,30,0,1,0,PType.ELECTRIC,"% chance to Paralyze foe"),
	DIVE(80,100,0,0,0,0,PType.WATER,"A normal attack"),
	//DOUBLE_BLAST(-1,60,30,0,1,0,PType.NORMAL,"% chance to Confuse foe"),
	//DOUBLE_EDGE(120,100,0,0,0,0,PType.NORMAL,"User takes 1/3 of damage inflicted"),
	DOUBLE_HIT(-1,90,0,0,0,0,PType.NORMAL,"Attacks twice"),
	//DOUBLE_JET(-1,85,0,0,0,0,PType.WATER,"Attacks 2-5 times"),
	DOUBLE_KICK(-1,100,0,0,0,0,PType.FIGHTING,"Attacks twice"),
	//DOUBLE_PUNCH(-1,85,0,0,0,0,PType.FIGHTING,"Attacks twice"),
	DOUBLE_SLAP(-1,95,0,0,0,0,PType.NORMAL,"Attacks 2-5 times"),
	//DOUBLE_SLICE(-1,80,15,0,0,0,PType.STEEL,"% to cause foe to Bleed; attacks twice"),
	DOUBLE_TEAM(0,1000,0,0,2,0,PType.NORMAL,"Raises user's Evasion by 1"),
	DRACO_METEOR(140,100,100,0,1,0,PType.DRAGON,"% to lower user's Sp.Atk by 2"),
	DRAGON_BREATH(60,100,30,0,1,0,PType.DRAGON,"% chance to Paralyze foe"),
	DRAGON_CLAW(80,100,0,1,0,0,PType.DRAGON,"Boosted Crit rate"),
	DRAGON_DANCE(0,1000,0,0,2,0,PType.DRAGON,"Raises user's Attack and Speed by 1"),
	DRAGON_DARTS(-1,100,0,0,0,0,PType.DRAGON,""), // TODO
	DRAGON_PULSE(85,100,0,0,1,0,PType.DRAGON,"A normal attack"),
	DRAGON_RAGE(0,100,0,0,1,0,PType.DRAGON,"Always does 40 HP damage"),
	DRAGON_RUSH(100,75,20,0,0,0,PType.DRAGON,"% chance of causing foe to flinch"),
	DRAGON_TAIL(75,100,0,0,2,-6,PType.DRAGON,""), // TODO
	DRAIN_PUNCH(75,100,0,0,0,0,PType.FIGHTING,""),
	DRAINING_KISS(50,100,0,0,1,0,PType.LIGHT,""),
	DREAM_EATER(100,100,0,0,1,0,PType.PSYCHIC,"Only works if target is asleep. Heals 50% of damage dealt to foe"),
	DRILL_PECK(80,100,0,1,0,0,PType.FLYING,"Boosted Crit rate"), //TODO GIVE TO BIRDS
	DRILL_RUN(80,95,0,1,0,0,PType.GROUND,"Boosted Crit rate"),
	//DUAL_STAB(-1,80,15,0,0,0,PType.STEEL,"% chance to cause foe to Bleed; attacks twice"),
	DUAL_CHOP(40,90,0,0,0,0,PType.DRAGON,""), //TODO
	EARTH_POWER(90,100,10,0,1,0,PType.GROUND,"% chance to lower foe's Sp.Def by 1"),
	EARTHQUAKE(100,100,0,0,0,0,PType.GROUND,"A normal attack"),
	ELECTRIC_TERRAIN(0,1000,0,0,2,0,PType.ELECTRIC,"Sets the terrain to ELECTRIC for 5 turns"),
	ELECTROBALL(-1,100,0,0,1,0,PType.ELECTRIC,"Power is higher the faster the user is than the target"),
	//ELECTROEXPLOSION(300,100,0,0,1,0,PType.ELECTRIC,"User faints. Bypasses Ground's immunity to Electric"),
	ELEMENTAL_SPARKLE(45,90,0,0,1,0,PType.MAGIC,""),
	EMBER(40,100,10,0,1,0,PType.FIRE,"% chance to Burn foe"),
	ENCORE(0,100,0,0,2,0,PType.NORMAL,""),
	ENDEAVOR(0,100,0,0,0,0,PType.NORMAL,""),
	ENDURE(0,1000,0,0,2,4,PType.NORMAL,""),
	ENERGY_BALL(90,100,10,0,1,0,PType.GRASS,""),
	ENTRAINMENT(0,100,0,0,2,0,PType.NORMAL,"Changes foe's ability to the user's"),
	ERUPTION(-1,100,0,0,1,0,PType.FIRE,"Power is higher the more HP the user has"),
	EXPANDING_FORCE(-1,100,0,0,1,0,PType.PSYCHIC,"Power is doubled if the terrain is PSYCHIC"), // TODO
	EXPLOSION(250,100,0,0,0,0,PType.NORMAL,"User faints"),
	EXTRASENSORY(80,100,10,0,1,0,PType.PSYCHIC,""),
	METRONOME(-1,1000,0,0,1,0,PType.MAGIC,"Selects a random move!"),
	EXTREME_SPEED(70,100,0,0,0,2,PType.FLYING,"Always goes first"),
	FAKE_OUT(40,100,100,0,0,3,PType.NORMAL,""),
	FAKE_TEARS(0,100,0,0,2,0,PType.DARK,"Lowers foe's Sp.Def by 2"),
	FAILED_SUCKER(0,100,0,0,0,0,PType.DARK,"If you're seeing this, something went horribly wrong"),
	FALSE_SURRENDER(80,1000,0,0,0,0,PType.DARK,""),
	FATAL_BIND(70,85,100,0,0,0,PType.BUG,"Causes foe to faint in 3 turns"), // TODO
	FEATHER_DANCE(0,100,0,0,2,0,PType.FLYING,"Lowers foe's Attack by 2"),
	FEINT(30,100,0,0,0,2,PType.NORMAL,""), // TODO
	FEINT_ATTACK(60,1000,0,0,0,0,PType.DARK,"This attack always hits"),
	FALSE_SWIPE(40,100,0,0,0,0,PType.NORMAL,"Always leaves the foe with at least 1 HP"),
	FELL_STINGER(50,100,0,0,0,0,PType.BUG,""),
	FIERY_DANCE(80,100,50,0,2,0,PType.FIRE,""),
	FIRE_BLAST(120,85,10,0,1,0,PType.FIRE,"% chance to Burn foe"),
	//FIRE_CHARGE(75,90,10,0,0,0,PType.FIRE,"% of flinching and/or Burning foe"),
	//FIRE_DASH(0,100,100,0,0,0,PType.FIRE,"% to Burn foe, user faints. Damage equals user's remaining HP"),
	FIRE_FANG(65,95,10,0,0,0,PType.FIRE,"% of flinching and/or Burning foe"),
	FIRE_PUNCH(75,100,10,0,0,0,PType.FIRE,"% to Burn foe"),
	FIRE_SPIN(35,85,100,0,1,0,PType.FIRE,"% to spin foe for 2-5 turns. While foe is spun, it takes 1/16 HP in damage, and cannot switch"),
	//FIRE_TAIL(85,90,10,0,0,0,PType.FIRE,"% to Burn foe"),
	//FIREBALL(-1,100,10,0,1,0,PType.FIRE,"% chance to Burn foe, damage is doubled if foe is Burned"),
	FIRST_IMPRESSION(90,100,0,0,0,1,PType.BUG,"Always attacks first, fails after the first turn a user is out in battle"),
	FISSURE(0,30,0,0,0,0,PType.GROUND,""),
	FLAIL(-1,100,0,0,0,0,PType.NORMAL,"Power is higher the lower HP the user has"),
	FLAME_BURST(110,100,100,0,1,0,PType.FIRE,"% to inflict burn, but user becomes Confused"),
	FLAME_CHARGE(50,100,100,0,0,0,PType.FIRE,""),
	FLAME_WHEEL(60,100,10,0,0,0,PType.FIRE,"% to Burn foe"),
	FLAMETHROWER(90,100,10,0,1,0,PType.FIRE,"% to Burn foe"),
	FLARE_BLITZ(120,100,10,0,0,0,PType.FIRE,"% to Burn foe, user takes 1/3 of damage inflicted"),
	FLASH(0,100,0,0,2,0,PType.LIGHT,"Lowers foe's Accuracy by 1, and raises user's Sp.Atk by 1"),
	FLASH_CANNON(80,100,10,0,1,0,PType.STEEL,"% chance to lower foe's Sp.Def by 1"),
	FLASH_RAY(40,100,50,0,1,0,PType.LIGHT,""),
	FLATTER(0,100,100,0,2,0,PType.DARK,"Confuses foe, and raises their Sp.Atk by 2"),
	FLY(90,100,0,0,0,0,PType.FLYING,"A normal attack"),
	FOCUS_BLAST(120,70,0,0,1,0,PType.FIGHTING,""),
	FOCUS_ENERGY(0,1000,0,0,2,0,PType.NORMAL,""),
	FOCUS_PUNCH(150,100,0,0,0,0,PType.FIGHTING,""), // TODO
	FORCE_PALM(60,100,30,0,0,0,PType.FIGHTING,""),
	FORESIGHT(0,1000,0,0,2,0,PType.MAGIC,"Indentifies foe, replacing their Ghost typing with Normal if they have it. It also raises user's Accuracy by 1 stage"),
	FORESTS_CURSE(0,100,0,0,2,0,PType.GRASS,""),
	FOUL_PLAY(95,100,0,0,0,0,PType.DARK,""),
	FREEZE_DRY(70,100,10,0,1,0,PType.ICE,""),
	FREEZING_GLARE(90,100,20,0,1,0,PType.PSYCHIC,""),
	FRENZY_PLANT(150,90,0,0,1,0,PType.GRASS,"User must rest after using this move"),
	FRUSTERATION(102,100,0,0,0,0,PType.NORMAL,"A normal attack"),
	FURY_ATTACK(-1,85,0,0,0,0,PType.NORMAL,"Attacks 2-5 times"),
	FURY_CUTTER(-1,95,0,0,0,0,PType.BUG,"Power increases the more times this move is used in succession"),
	FURY_SWIPES(-1,80,0,0,0,0,PType.NORMAL,"Attacks 2-5 times"),
	FUSION_BOLT(100,100,0,0,0,0,PType.ELECTRIC,""),
	FUSION_FLARE(100,100,0,0,1,0,PType.FIRE,""),
	FUTURE_SIGHT(0,100,0,0,2,0,PType.PSYCHIC,""), // TODO
	//GALAXY_ATTACK(115,90,30,0,0,0,PType.MAGIC,"% chance to inflict the foe with a random Status condition"),
	GALAXY_BLAST(90,100,0,0,1,0,PType.GALACTIC,""),
	GASTRO_ACID(0,100,0,0,2,0,PType.POISON,""),
	GENESIS_SUPERNOVA(120,95,0,0,1,0,PType.GALACTIC,""),
	GEOMANCY(0,1000,0,0,2,0,PType.LIGHT,""),
	GIGA_DRAIN(75,100,0,0,1,0,PType.GRASS,"Heals 50% of damage dealt to foe"),
	//GIGA_HIT(110,75,50,0,0,0,PType.FIGHTING,"% chance to Paralyze foe"),
	GIGA_IMPACT(150,90,0,0,0,0,PType.NORMAL,"User must rest after using this move"),
	GLACIATE(65,95,100,0,1,0,PType.ICE,""),
	GLARE(0,100,0,0,2,0,PType.NORMAL,"Paralyzes foe"),
	GLITTER_DANCE(0,1000,0,0,2,0,PType.LIGHT,"Raises user's Sp.Atk and Sp.Def by 1"),
	GLITTERING_SWORD(95,100,20,0,0,0,PType.LIGHT,""),
	GLITTERING_TORNADO(55,100,30,0,1,0,PType.LIGHT,""),
	GLITZY_GLOW(80,100,30,0,1,0,PType.LIGHT,""),
	GRASS_KNOT(80,100,0,0,1,0,PType.GRASS,"A normal attack"), // TODO
	GRASS_WHISTLE(0,55,0,0,2,0,PType.GRASS,""),
	GRASSY_TERRAIN(0,1000,0,0,2,0,PType.GRASS,"Sets the terrain to GRASSY for 5 turns"),
	//GRASS_PUNCH(80,100,0,0,0,0,PType.GRASS,"A normal attack"),
	GRAVITY(0,1000,0,0,2,0,PType.GALACTIC,"Sets GRAVITY for 5 turns"),
	GROWL(0,100,0,0,2,0,PType.NORMAL,"Lowers foe's Attack by 1"),
	GROWTH(0,1000,0,0,2,0,PType.GRASS,"Raises user's Attack and Sp.Atk by 1"),
	GUILLOTINE(0,30,0,0,0,0,PType.NORMAL,""),
	GUNK_SHOT(120,70,30,0,1,0,PType.POISON,"% chance to Poison foe"),
	//GUNSHOT(70,60,0,2,0,0,PType.STEEL,"25% chance to Crit. If it Crits, foe is Bleeding"),
	GUST(40,100,0,0,1,0,PType.FLYING,"A normal attack"),
	GYRO_BALL(-1,100,0,0,0,0,PType.STEEL,"The lower the user's speed compared to the foe, the more power"),
	SNOWSCAPE(0,1000,0,0,2,0,PType.ICE,"Sets the weather to SNOW for 5 turns"),
	HAMMER_ARM(100,90,100,0,0,0,PType.FIGHTING,"% chance to lower user's speed by 1"),
	HARDEN(0,1000,0,0,2,0,PType.NORMAL,"Raises user's Defense by 1"),
	HAZE(0,1000,0,0,2,0,PType.GHOST,"Clears all stat changes on the field"),
	HEAD_SMASH(150,80,0,0,0,0,PType.ROCK,"User takes 1/3 of damage inflicted"),
	HEADBUTT(70,100,30,0,0,0,PType.NORMAL,"% chance of causing foe to flinch"),
	HEAL_PULSE(0,1000,0,0,2,0,PType.PSYCHIC,"Heals foe by 50% HP"),
	HEALING_WISH(0,1000,0,0,2,0,PType.PSYCHIC,""), // TODO
	HEAT_CRASH(-1,100,0,0,0,0,PType.FIRE,""), // TODO
	HEAT_WAVE(95,90,10,0,1,0,PType.FIRE,"% to Burn foe"),
	HEAVY_SLAM(-1,100,0,0,0,0,PType.STEEL,""), // TODO
	HEX(-1,100,0,0,1,0,PType.GHOST,""), // TODO
	HI_JUMP_KICK(130,90,0,0,0,0,PType.FIGHTING,"If this attack misses, user takes 50% of its max HP"),
	HIDDEN_POWER(60,100,0,0,1,0,PType.NORMAL,""),
	HONE_CLAWS(0,1000,0,0,2,0,PType.DARK,"Raises user's Attack and Accuracy by 1"),
	HORN_ATTACK(65,100,0,0,0,0,PType.NORMAL,"A normal attack"), // recharge
	HORN_DRILL(0,30,0,0,0,0,PType.NORMAL,"If this move hits, it always K.Os foe"),
	HORN_LEECH(75,100,0,0,0,0,PType.GRASS,""),
	HOWL(0,1000,0,0,2,0,PType.NORMAL,"Raises user's Attack by 1"),
	HURRICANE(110,70,30,0,1,0,PType.FLYING,""),
	HYDRO_CANNON(150,90,0,0,1,0,PType.WATER,"User must rest after using this move"),
	HYDRO_PUMP(120,80,0,0,1,0,PType.WATER,"A normal attack"),
	HYPER_BEAM(150,90,0,0,1,0,PType.NORMAL,"User must rest after using this move"),
	HYPER_FANG(80,90,10,0,0,0,PType.NORMAL,"% of causing foe to flinch"),
	HYPNOSIS(0,60,0,0,2,0,PType.PSYCHIC,"Causes foe to sleep"),
	ICE_BALL(-1,90,0,0,0,0,PType.ICE,""),
	ICE_BEAM(90,100,10,0,1,0,PType.ICE,""),
	ICE_FANG(65,95,10,0,0,0,PType.ICE,""),
	ICE_PUNCH(75,100,10,0,0,0,PType.ICE,""),
	ICE_SHARD(40,100,0,0,0,1,PType.ICE,"Always goes first"),
	ICE_SPINNER(80,100,100,0,0,0,PType.ICE,""),
	ICICLE_CRASH(85,90,30,0,0,0,PType.ICE,""),
	ICICLE_SPEAR(25,100,0,0,0,0,PType.ICE,""), // TODO
	ICY_WIND(55,95,100,0,1,0,PType.ICE,""),
	//IGNITE(0,75,0,0,2,0,PType.FIRE,"Burns foe"),
	IMPRISON(0,1000,0,0,2,0,PType.PSYCHIC,""), // TODO
	INCINERATE(60,100,0,0,1,0,PType.FIRE,""),
	INFERNO(100,50,100,0,1,0,PType.FIRE,""),
	INFESTATION(20,100,100,0,1,0,PType.BUG,""),
	INGRAIN(0,1000,0,0,2,0,PType.GRASS,""),
	//INJECT(55,100,100,0,0,0,PType.BUG,"% to Poison foe, heals 50% of damage dealt"),
	IRON_BLAST(85,90,30,0,1,0,PType.STEEL,""),
	IRON_DEFENSE(0,1000,0,0,2,0,PType.STEEL,"Raises user's Defense by 2"),
	IRON_HEAD(80,100,30,0,0,0,PType.STEEL,"% of causing foe to flinch"),
	IRON_TAIL(100,75,30,0,0,0,PType.STEEL,"% of lowering foe's Defense by 1"),
	JAW_LOCK(80,100,100,0,0,0,PType.DARK,""),
	KARATE_CHOP(50,100,0,1,0,1,PType.FIGHTING,"Boosted Crit rate"),
	LAVA_PLUME(80,100,30,0,1,0,PType.FIRE,"% to Burn foe"),
	//LEAF_BALL(75,95,0,0,1,0,PType.GRASS,"A normal attack"),
	LEAF_BLADE(90,100,0,1,0,0,PType.GRASS,"Boosted Crit rate"),
	//LEAF_GUST(50,100,0,0,1,0,PType.GRASS,"A normal attack"),
	//LEAF_KOBE(75,90,100,0,0,0,PType.GRASS,"% to Paralyze foe"),
	//LEAF_PULSE(85,75,100,0,1,0,PType.GRASS,"% to lower foe's accuracy, 50% to cause foe to fall asleep"),
	//LEAF_SLAP(30,100,0,0,0,0,PType.GRASS,"A normal attack"),
	LEAF_STORM(130,90,100,0,1,0,PType.GRASS,"% to lower user's Sp.Atk by 2"),
	LEAF_TORNADO(65,90,50,0,1,0,PType.GRASS,"% to lower foe's Accuracy by 1"),
	LEAFAGE(40,100,0,0,0,0,PType.GRASS,""),
	LEECH_LIFE(80,100,0,0,0,0,PType.BUG,"Heals 50% of damage dealt"), // recoil
	LEECH_SEED(0,90,0,0,2,0,PType.GRASS,"At the end of every turn, user steals 1/8 of foe's max HP"),
	LEER(0,100,0,0,2,0,PType.NORMAL,"Lowers foe's Defense by 1"),
	LICK(20,100,30,0,0,0,PType.GHOST,"% to Paralyze foe"),
	//LIGHTNING_HEADBUTT(90,95,30,0,0,0,PType.ELECTRIC,"% of Paralysis and/or causing foe to flinch. User takes 1/3 of damage dealt as recoil"),
	LIFE_DEW(0,1000,0,0,2,0,PType.WATER,"Restores 25% HP"),
	LIGHT_BEAM(60,100,20,0,1,0,PType.LIGHT,""),
	LIGHT_OF_RUIN(140,90,0,0,1,0,PType.LIGHT,""),
	LIGHT_SCREEN(0,1000,0,0,2,0,PType.PSYCHIC,""),
	LIQUIDATION(85,100,20,0,0,0,PType.WATER,""),
	LOAD_FIREARMS(0,100,0,0,2,0,PType.STEEL,""), // TODO
	LOCK_ON(0,1000,0,0,2,0,PType.NORMAL,"Raises user's Accuracy by 6"),
	LOVELY_KISS(0,75,0,0,2,0,PType.NORMAL,""),
	LOW_KICK(60,100,100,0,0,0,PType.FIGHTING,"% to lower foe's Speed by 1"), //TODO
	LOW_SWEEP(65,100,100,0,0,0,PType.FIGHTING,""),
	LUNAR_DANCE(0,1000,0,0,2,0,PType.PSYCHIC,""), // TODO
	LUSTER_PURGE(70,100,50,0,1,0,PType.LIGHT,""),
	MACH_PUNCH(40,100,0,0,0,1,PType.FIGHTING,"Always goes first"),
	MACHETE_JAB(75,80,100,0,0,0,PType.STEEL,"% to cause foe to Bleed"),
	MAGIC_BLAST(30,100,0,0,1,0,PType.MAGIC,"A random Rock, Ground or Grass move is also used"),
	MAGIC_CRASH(110,80,100,0,0,0,PType.MAGIC,"% to inflict foe with a random Status condition. User must rest after using"),
	MAGIC_FANG(70,95,75,0,0,0,PType.MAGIC,"% to flinch foe if this move is Super-Effective against it"),
	MAGIC_POWDER(0,100,0,0,2,0,PType.MAGIC,""),
	MAGIC_REFLECT(0,1000,0,0,2,0,PType.MAGIC,"Foe's next attack will be reflected against them. Can be used every other turn"),
	MAGIC_TOMB(90,100,0,0,1,0,PType.MAGIC,"A normal attack"),
	MAGICAL_LEAF(60,1000,0,0,1,0,PType.GRASS,"This move will never miss"),
	MAGNET_BOMB(60,1000,0,0,0,0,PType.STEEL,""),
	MAGNET_RISE(0,1000,0,0,2,0,PType.STEEL,"User will float for 5 turns, causing it to be immune to all Ground-type attacks"),
	MAGNITUDE(-1,100,0,0,0,0,PType.GROUND,"A random Magnitude between 4-10 will be used, corresponding to its power"),
	MEAN_LOOK(0,100,0,0,2,0,PType.NORMAL,""),
	MEGA_DRAIN(40,100,0,0,1,0,PType.GRASS,"Heals 50% of damage dealt"),
	MEGAHORN(120,85,0,0,0,0,PType.BUG,""),
	MEMENTO(0,100,0,0,2,0,PType.DARK,""), // TODO
	METAL_CLAW(50,95,10,0,0,0,PType.STEEL,""),
	//MEGA_KICK(70,100,60,0,0,0,PType.FIGHTING,"% to Paralyze foe"),
	//MEGA_PUNCH(70,100,60,0,0,0,PType.FIGHTING,"% to Paralyze foe"),
	//MEGA_SWORD(70,100,60,0,0,0,PType.STEEL,"% to Paralyze foe"),
	METAL_SOUND(0,100,0,0,2,0,PType.STEEL,"Lowers foe's Sp.Def by 2"),
	METEOR_ASSAULT(120,100,100,0,0,0,PType.GALACTIC,""),
	METEOR_MASH(90,90,20,0,0,0,PType.STEEL,""),
	MINIMIZE(0,1000,0,0,2,0,PType.GHOST,"Raises user's Evasion by 2"),
	MIRROR_SHOT(65,85,30,0,1,0,PType.GALACTIC,""),
	MIRROR_MOVE(0,1000,0,0,1,0,PType.FLYING,"Uses the move last used by the foe, fails if foe hasn't used a move yet"),
	MIST_BALL(70,100,50,0,1,0,PType.PSYCHIC,""),
	MOLTEN_CONSUME(0,100,0,0,0,0,PType.FIRE,""), // TODO
	MOLTEN_LAIR(0,100,0,0,2,0,PType.FIRE,""), // TODO
	MOLTEN_STEELSPIKE(100,100,30,0,1,0,PType.FIRE,""),
	MOONBLAST(95,100,30,0,1,0,PType.LIGHT,""),
	MOONLIGHT(0,1000,0,0,2,0,PType.LIGHT,"Restores 1/2 of user's max HP"),
	MORNING_SUN(0,1000,0,0,2,0,PType.LIGHT,""),
	MUD_BOMB(65,85,30,0,1,0,PType.GROUND,"% to lower foe's Accuracy by 1"),
	MUD_SHOT(55,95,100,0,1,0,PType.GROUND,""),
	MUD_SLAP(20,100,100,0,1,0,PType.GROUND,"% to lower foe's Accuracy by 1"),
	MUD_SPORT(0,1000,0,0,2,0,PType.GROUND,"Does nothing"),
	MUDDY_WATER(90,85,30,0,1,0,PType.WATER,""),
	MYSTICAL_FIRE(75,100,100,0,1,0,PType.FIRE,""),
	NASTY_PLOT(0,1000,0,0,2,0,PType.DARK,""),
	NEEDLE_ARM(60,100,30,0,1,0,PType.GRASS,""),
	//NEEDLE_SPRAY(55,95,10,0,0,0,PType.POISON,"% to Poison or Paralyze foe"),
	//NIBBLE(10,100,0,0,0,0,PType.NORMAL,"A normal attack"),
	NIGHT_DAZE(85,95,40,0,0,0,PType.DARK,""),
	NIGHT_SHADE(0,100,0,0,1,0,PType.GHOST,"Deals damage equal to user's level"),
	NIGHT_SLASH(70,100,0,1,0,0,PType.DARK,"Boosted Crit rate"),
	NIGHTMARE(0,100,0,0,2,0,PType.GHOST,"Foe loses 1/4 of max HP each turn; wears off when foe wakes up"),
	NO_RETREAT(0,1000,0,0,2,0,PType.FIGHTING,""), // TODO
	NOBLE_ROAR(0,100,0,0,2,0,PType.NORMAL,""),
	NUZZLE(20,100,100,0,0,0,PType.ELECTRIC,""),
	OBSTRUCT(0,1000,0,0,2,4,PType.DARK,""), // TODO
	ODOR_SLEUTH(0,1000,0,0,2,0,PType.NORMAL,"Indentifies foe, replacing their Ghost typing with Normal if they have it. It also lowers foe's Evasion by 1"),
	OUTRAGE(120,100,0,0,0,0,PType.DRAGON,"User is locked into this move for 2-3 turns, Confuses user when the effect is done"),
	OVERHEAT(140,90,100,0,1,0,PType.FIRE,"% to lower user's Sp.Atk by 2"),
	PARABOLIC_CHARGE(65,100,0,0,1,0,PType.ELECTRIC,""),
	PAYBACK(-1,100,0,0,0,0,PType.DARK,""), // TODO
	PECK(35,100,0,0,0,0,PType.FLYING,"A normal attack"),
	PERISH_SONG(0,1000,0,0,2,0,PType.GHOST,"All Pokemon hearing this song will faint in 3 turns"),
	PETAL_BLIZZARD(90,100,0,0,0,0,PType.GRASS,""),
	PETAL_DANCE(120,100,0,0,1,0,PType.GRASS,""),
	PHANTOM_FORCE(100,100,0,0,0,0,PType.GHOST,""),
	PHOTON_GEYSER(130,90,100,0,1,0,PType.LIGHT,""),
	PIN_MISSILE(25,95,0,0,0,0,PType.BUG,""), // TODO
	PISTOL_POP(110,70,0,0,0,0,PType.STEEL,""),
	PLASMA_FISTS(100,100,0,0,0,0,PType.ELECTRIC,""),
	PLAY_NICE(0,100,0,0,2,0,PType.NORMAL,""),
	PLAY_ROUGH(90,90,10,0,0,0,PType.LIGHT,""),
	PLUCK(60,100,0,0,0,0,PType.FLYING,""),
	//PHASE_SHIFT(0,1000,0,0,2,0,PType.MAGIC,"Switches user's type to Magic and the type of the move that the foe just used"),
	//POISON_BALL(65,80,100,0,0,0,PType.POISON,"% to Poison foe"),
	POISON_FANG(50,100,30,0,0,0,PType.POISON,"% to Poison and/or flinch foe"),
	POISON_GAS(0,80,0,0,2,0,PType.POISON,"Poisons foe"),
	POISON_JAB(80,100,30,0,0,0,PType.POISON,"% to Poison foe"),
	//POISON_POWDER(0,75,0,0,2,0,PType.POISON,"Poisons foe"),
	//POISON_PUNCH(75,100,10,0,0,0,PType.POISON,"% chance to Poison foe"),
	POISON_STING(15,100,30,0,0,0,PType.POISON,"% chance to Poison foe"),
	POP_POP(-1,100,0,0,0,0,PType.STEEL,""), // TODO
	//POISONOUS_WATER(95,85,30,0,1,0,PType.POISON,"% chance to Poison foe"),
	//POKE(10,100,0,0,0,0,PType.NORMAL,"A normal attack"),
	POUND(40,100,0,0,0,0,PType.NORMAL,"A normal attack"),
	POWDER_SNOW(40,100,30,0,1,0,PType.ICE,""),
	POWER_GEM(80,100,0,0,1,0,PType.ROCK,""),
	POWER_WHIP(120,85,0,0,0,0,PType.GRASS,""),
	POWER_UP_PUNCH(40,100,100,0,0,0,PType.FIGHTING,""),
	PRISMATIC_LASER(100,100,0,0,1,0,PType.LIGHT,""),
	PROTECT(0,1000,0,0,2,4,PType.NORMAL,""), // TODO
	PSYBEAM(65,100,10,0,1,0,PType.PSYCHIC,""),
	PSYCHIC(90,100,10,0,1,0,PType.PSYCHIC,""),
	PSYCHIC_FANGS(85,100,100,0,0,0,PType.PSYCHIC,""), // TODO
	PSYCHIC_TERRAIN(0,1000,0,0,2,0,PType.PSYCHIC,""),
	PSYCHO_CUT(70,100,0,1,0,0,PType.PSYCHIC,"Boosted Crit rate"),
	PSYSHOCK(80,100,0,0,1,0,PType.PSYCHIC,""),
	PSYWAVE(0,100,0,0,2,0,PType.PSYCHIC,""),
	//PUNCH(40,90,0,0,0,0,PType.FIGHTING,"A normal attack"),
	PURSUIT(40,100,0,0,0,0,PType.DARK,"A normal attack"), //TODO
	QUICK_ATTACK(40,100,0,0,0,1,PType.NORMAL,"Always attacks first"),
	QUIVER_DANCE(0,1000,0,0,2,0,PType.BUG,""),
	RAGE(-1,100,0,0,0,0,PType.NORMAL,"Power increases the more times this move is used in succession"),
	RAIN_DANCE(0,1000,0,0,2,0,PType.WATER,""),
	RAPID_SPIN(20,100,100,0,0,0,PType.NORMAL,"% to raise user's Speed by 1, and frees user of being Spun"),
	RAZOR_LEAF(55,95,0,1,0,0,PType.GRASS,"Boosted Crit rate"),
	RAZOR_SHELL(75,95,50,0,0,0,PType.WATER,""),
	REBOOT(0,1000,0,0,2,0,PType.STEEL,"Clears user or any Status condition, and raises user's Speed by 1"),
	RECOVER(0,1000,0,0,2,0,PType.NORMAL,""),
	RED_NOSE_BOOST(0,1000,0,0,2,0,PType.MAGIC,""),
	REFLECT(0,1000,0,0,2,0,PType.PSYCHIC,""),
	REST(0,1000,0,0,2,0,PType.PSYCHIC,""),
	RETURN(-1,100,0,0,0,0,PType.NORMAL,""), // TODO
	REVENGE(-1,100,0,0,0,0,PType.FIGHTING,"Power is doubled if user is slower than foe"),
	REVERSAL(-1,100,0,0,2,0,PType.FIGHTING,""), // TODO
	ROAR(0,1000,0,0,2,-6,PType.NORMAL,""), // TODO
	//ROCK_BLADE(80,100,0,1,0,0,PType.ROCK,"Boosted Crit rate"),
	ROCK_BLAST(-1,90,0,0,0,0,PType.ROCK,"Hits 2-5 times"),
	ROCK_POLISH(0,1000,0,0,2,0,PType.ROCK,"Raises user's Speed by 2"),
	ROCK_SLIDE(75,90,30,0,0,0,PType.ROCK,"% of causing foe to flinch"),
	ROCK_SMASH(40,100,50,0,0,0,PType.FIGHTING,"% to lower foe's Defense by 1"),
	ROCK_THROW(50,90,0,0,0,0,PType.ROCK,"A normal attack"),
	ROCK_TOMB(60,80,100,0,0,0,PType.ROCK,"% to lower foe's Speed by 1"),
	ROCK_WRECKER(150,90,0,0,0,0,PType.ROCK,"User takes 1/3 of damage dealt as recoil"),
	ROCKFALL_FRENZY(0,100,0,0,2,0,PType.ROCK,""), // TODO
	//ROCKET(120,75,50,0,0,0,PType.STEEL,"% to Paralyze foe"),
	ROLLOUT(-1,90,0,0,0,0,PType.ROCK,"Attacks up to 5 times, damage doubles each time. While active, user cannot switch out"),
	ROOST(0,1000,0,0,2,0,PType.FLYING,"Restores 1/2 of user's max HP"),
	//ROOT_CRUSH(105,90,100,0,0,0,PType.ROCK,"% to Paralyze foe"),
	ROOT_KICK(60,95,0,0,0,0,PType.ROCK,"A normal attack"),
	ROUND(60,100,0,0,1,0,PType.NORMAL,""),
	SACRED_FIRE(100,95,50,0,0,0,PType.FIRE,""),
	SACRED_SWORD(90,100,0,0,0,0,PType.FIGHTING,""),
	SAFEGUARD(0,1000,0,0,2,0,PType.NORMAL,""), // TODO
	SAND_ATTACK(0,100,0,0,2,0,PType.GROUND,"Lowers foe's Accuracy by 1"),
	SANDSTORM(0,1000,0,0,2,0,PType.ROCK,""),
	SCALD(80,100,30,0,2,0,PType.WATER,""),
	SCALE_SHOT(25,90,0,0,2,0,PType.DRAGON,""), // TODO
	SCARY_FACE(0,100,0,0,2,0,PType.NORMAL,"Lowers foe's Speed by 2"),
	SCORCHING_SANDS(70,100,30,0,1,0,PType.GROUND,""),
	SCRATCH(40,100,0,0,0,0,PType.NORMAL,"A normal attack"),
	SCREECH(0,85,0,0,2,0,PType.NORMAL,"Lowers foe's Defense by 2"),
	SEA_DRAGON(0,1000,0,0,2,0,PType.MAGIC,""), // TODO
	SEISMIC_TOSS(0,100,0,0,0,0,PType.FIGHTING,"Damage dealt is equal to the user's level"),
	SELF_DESTRUCT(200,100,0,0,0,0,PType.NORMAL,"User faints"),
	SHADOW_BALL(80,100,30,0,1,0,PType.GHOST,"% to lower foe's Sp.Def by 1"),
	SHADOW_CLAW(70,100,0,1,0,0,PType.GHOST,""),
	SHADOW_PUNCH(60,1000,0,0,0,0,PType.GHOST,""),
	SHADOW_SNEAK(40,100,0,0,0,1,PType.GHOST,"Always attacks first"),
	SHEER_COLD(0,30,0,0,1,0,PType.ICE,""),
	//SHELL_BASH(70,100,0,1,0,0,PType.NORMAL,"User takes 1/3 of damage dealt as recoil"),
	SHELL_SMASH(0,1000,0,0,2,0,PType.NORMAL,"Raises user's Attack, Sp.Atk, and Speed by 2, at the cost of lowering its Defense and Sp.Def by 1"),
	SHIFT_GEAR(0,1000,0,0,2,0,PType.STEEL,""),
	//SHOCK(15,100,100,0,1,0,PType.ELECTRIC,"% to Paralyze foe"),
	SHOCK_WAVE(60,1000,0,0,1,0,PType.ELECTRIC,"This attack never misses"),
	SILVER_WIND(60,100,10,0,1,0,PType.BUG,""),
	//SHURIKEN(75,85,50,0,0,0,PType.STEEL,"% to cause foe to Bleed"),
	SKULL_BASH(100,100,100,0,0,0,PType.NORMAL,"% to raise the user's Defense by 1, user must charge on the first turn"),
	SKY_ATTACK(140,90,30,1,0,0,PType.FLYING,"% chance to flinch. User must charge up on the first turn, attacks on the second. Boosted Crit rate"),
	SKY_UPPERCUT(85,90,0,0,0,0,PType.FIGHTING,"A normal attack"),
	SLACK_OFF(0,1000,0,0,2,0,PType.NORMAL,""),
	SLAM(80,75,0,0,0,0,PType.NORMAL,"A normal attack"),
	//SLAP(20,100,0,0,0,0,PType.NORMAL,"A normal attack"),
	SLASH(70,100,0,1,0,0,PType.NORMAL,"Boosted Crit rate"),
	SLEEP_POWDER(0,75,0,0,2,0,PType.GRASS,"Foe falls asleep"),
	SLUDGE(65,100,30,0,1,0,PType.POISON,"% to Poison foe"),
	SLUDGE_BOMB(90,100,30,0,1,0,PType.POISON,"% to Poison foe"),
	SLUDGE_WAVE(95,100,10,0,1,0,PType.POISON,""),
	SMACK_DOWN(50,100,100,0,0,0,PType.ROCK,""), // TODO
	SMART_STRIKE(70,1000,0,0,0,0,PType.STEEL,""),
	//SMASH(70,90,0,0,0,0,PType.NORMAL,"A normal attack"),
	SMOG(20,70,50,0,1,0,PType.POISON,"% to Poison foe"),
	SMOKESCREEN(0,100,0,0,2,0,PType.NORMAL,"Lowers foe's accuracy by 1"),
	SNARL(55,95,100,0,1,0,PType.DARK,""),
	SNORE(50,100,0,0,1,0,PType.NORMAL,""), // TODO
	SOLAR_BEAM(120,100,0,0,1,0,PType.GRASS,"User must charge up on the first turn, attacks on the second"),
	SOLAR_BLADE(125,100,0,0,0,0,PType.GRASS,""),
	SPACE_BEAM(60,100,30,0,1,0,PType.GALACTIC,""),
	SPACIAL_REND(100,95,0,1,1,0,PType.GALACTIC,""),
	SPARK(65,100,30,0,0,0,PType.ELECTRIC,"% to Paralyze foe"),
	SPARKLE_STRIKE(80,1000,0,0,0,0,PType.MAGIC,""),
	SPARKLING_ARIA(90,100,100,0,1,0,PType.WATER,""),
	SPARKLING_TERRAIN(0,1000,0,0,2,0,PType.MAGIC,""),
	SPARKLING_WATER(0,100,0,0,2,0,PType.WATER,""), // TODO
	SPARKLY_SWIRL(70,100,10,0,1,0,PType.MAGIC,""),
	SPECTRAL_THIEF(90,100,100,0,0,0,PType.GHOST,""),
	SPEEDY_SHURIKEN(40,100,0,0,0,1,PType.STEEL,""),
	SPIKE_CANNON(20,100,0,0,0,0,PType.NORMAL,""), // TODO
	//SPIKE_JAB(55,80,100,0,0,0,PType.POISON,"% to Poison foe"),
	//SPIKE_SHOT(-1,100,0,0,0,0,PType.POISON,"Attacks 2-5 times"),
	SPIKES(0,1000,0,0,2,0,PType.GROUND,""),
	//SPIKE_SLAM(65,90,0,0,0,0,PType.NORMAL,"A normal attack"),
	SPIKY_SHIELD(0,1000,0,0,2,4,PType.GRASS,""), // TODO
	SPIRIT_BREAK(75,100,100,0,2,0,PType.LIGHT,""),
	SPLASH(0,1000,0,0,2,0,PType.NORMAL,""),
	STAR_STORM(110,85,0,0,1,0,PType.GALACTIC,"A normal attack"),
	STAR_STRUCK_ARCHER(75,85,0,3,0,0,PType.GALACTIC,""),
	STEALTH_ROCK(0,1000,0,0,2,0,PType.ROCK,""),
	STEEL_BEAM(140,95,100,0,1,0,PType.STEEL,""),
	STEEL_WING(70,90,10,0,0,0,PType.STEEL,""),
	STICKY_WEB(0,1000,0,0,2,0,PType.BUG,""),
	STOCKPILE(0,1000,0,0,2,0,PType.NORMAL,""),
	//STARE(0,100,0,0,2,0,PType.NORMAL,"Confuses foe, but raises foe's Attack by 1"), // recoil
	//STING(55,100,100,0,0,0,PType.BUG,"% to make foe Bleed"),
	STOMP(65,100,30,0,0,0,PType.NORMAL,"% of causing foe to flinch"),
	STONE_EDGE(100,80,0,1,0,0,PType.ROCK,"Boosted Crit rate"),
	STRENGTH_SAP(0,100,0,0,2,0,PType.GRASS,""),
	STRENGTH(80,100,0,0,0,0,PType.NORMAL,"A normal attack"),
	STRING_SHOT(0,100,0,0,2,0,PType.BUG,"Lowers foe's Speed by 2"),
	STRUGGLE_BUG(50,100,100,0,1,0,PType.BUG,""),
	//STRONG_ARM(90,85,30,0,0,0,PType.FIGHTING,"% chance of Paralyzing and/or causing foe to flinch"),
	STUN_SPORE(0,75,0,0,2,0,PType.GRASS,""),
	SUBMISSION(80,90,0,0,0,0,PType.FIGHTING,""),
	SUCKER_PUNCH(80,100,0,0,0,2,PType.DARK,"Always attacks first. Fails if foe didn't use an attacking move"),
	SUNNY_DAY(0,1000,0,0,2,0,PType.FIRE,""),
	SUNSTEEL_STRIKE(100,100,0,0,0,0,PType.STEEL,""),
	//SUPER_CHARGE(90,50,100,0,0,0,PType.ELECTRIC,"% of causing foe to flinch, user takes 1/3 of damage dealt as recoil"),
	SUPER_FANG(0,100,0,0,0,0,PType.NORMAL,"Halves foe's remaining HP"),
	SUPERCHARGED_SPLASH(0,100,0,0,2,0,PType.WATER,""), // TODO
	SUPERNOVA_EXPLOSION(200,100,0,0,1,0,PType.GALACTIC,""),
	SUPERPOWER(120,100,100,0,0,0,PType.FIGHTING,"% of lowering user's Attack and Defense by 1"),
	SUPERSONIC(0,55,0,0,2,0,PType.NORMAL,"Confuses foe"),
	SURF(95,100,0,0,1,0,PType.WATER,"A normal attack"),
	SWAGGER(0,85,0,0,2,0,PType.NORMAL,"Confuses foe, but raises foe's Attack by 2"),
	//SWEEP_KICK(60,95,100,0,0,0,PType.FIGHTING,"% to lower foe's Attack by 1"),
	SWEET_KISS(0,75,0,0,2,0,PType.LIGHT,""),
	SWEET_SCENT(0,1000,0,0,2,0,PType.NORMAL,""),
	SWIFT(60,1000,0,0,1,0,PType.MAGIC,"This attack never misses"),
	//SWORD_SLASH(75,90,0,1,0,0,PType.STEEL,"Boosted Crit rate"), // recoil
	//SWORD_SLICE(65,85,0,1,0,0,PType.STEEL,"Boosted Crit rate. If it Crits, foe is Bleeding"),
	SWORD_SPIN(50,95,100,0,0,0,PType.STEEL,"% to raise user's Attack by 1"),
	//SWORD_STAB(95,60,100,0,0,0,PType.STEEL,"% to cause foe to Bleed"),
	SWORDS_DANCE(0,1000,0,0,2,0,PType.NORMAL,"Raises user's Attack by 2"),
	SYNTHESIS(0,1000,0,0,2,0,PType.GRASS,"Restores 1/2 of user's max HP"),
	TACKLE(50,100,0,0,0,0,PType.NORMAL,"Really? You're looking what tackle does?"),
	//TAIL_WHACK(90,85,0,0,0,0,PType.NORMAL,"A normal attack"),
	TAIL_GLOW(0,1000,0,0,2,0,PType.BUG,""),
	TAIL_WHIP(0,100,0,0,2,0,PType.NORMAL,"Lowers foe's Defense by 1"),
	TAILWIND(0,1000,0,0,2,0,PType.FLYING,""),
	TAKE_DOWN(90,85,0,0,0,0,PType.NORMAL,"User takes 1/3 of damage dealt as recoil"),
	TAKE_OVER(0,100,0,0,2,0,PType.GHOST,"Foe's next attack is used on itself. Can be used once every other turn"),
	TAUNT(0,100,0,0,2,0,PType.DARK,""), // TODO
	TEETER_DANCE(0,100,0,0,2,0,PType.NORMAL,""),
	TELEPORT(0,1000,0,0,2,-6,PType.PSYCHIC,""), // TODO
	THRASH(120,100,0,0,0,0,PType.NORMAL,""),
	THROAT_CHOP(80,100,100,0,0,0,PType.DARK,""), // TODO
	THUNDER(120,70,30,0,1,0,PType.ELECTRIC,"% of Paralyzing foe"),
	THUNDER_FANG(65,95,10,0,0,0,PType.ELECTRIC,"% of Paralyzing and/or flinching foe"),
	//THUNDER_KICK(80,90,10,0,0,0,PType.ELECTRIC,"% of Paralyzing foe"),
	THUNDER_PUNCH(75,100,10,0,0,0,PType.ELECTRIC,"% of Paralyzing foe"),
	THUNDER_WAVE(0,100,0,0,2,0,PType.ELECTRIC,"Paralyzes foe"),
	THUNDERBOLT(95,100,10,0,1,0,PType.ELECTRIC,"% of Paralyzing foe"), // recoil
	THUNDERSHOCK(40,100,10,0,1,0,PType.ELECTRIC,"% of Paralyzing foe"),
	TOPSY_TURVY(0,1000,0,0,2,0,PType.DARK,""),
	TORMENT(0,100,0,0,2,0,PType.DARK,""), // TODO
	//TIDAL_WAVE(-1,100,0,0,1,0,PType.WATER,"Picks a random tide level from the time of day. Morning = 90, Day = 50, and Evening = 130 Base Power"),
	TORNADO_SPIN(60,95,100,0,0,0,PType.FIGHTING,"% to raise user's Speed and Accuracy by 1, and frees user of being Spun"),
	TOXIC(0,100,0,0,2,0,PType.POISON,"Poisons foe"),
	TOXIC_SPIKES(0,1000,0,0,2,0,PType.POISON,""),
	TRI_ATTACK(80,100,20,0,1,0,PType.NORMAL,""),
	TRICK_ROOM(0,1000,0,0,2,-7,PType.PSYCHIC,""),
	TWINKLE_TACKLE(85,90,20,0,0,0,PType.MAGIC,""),
	TWINNEEDLE(25,100,20,0,0,0,PType.BUG,""),
	TWISTER(40,100,10,0,1,0,PType.DRAGON,"% of causing foe to flinch"),
	UNSEEN_STRANGLE(0,100,0,0,2,0,PType.DARK,""), // TODO
	U_TURN(70,100,0,0,0,0,PType.BUG,""), // TODO
	VACUUM_WAVE(40,100,0,0,1,1,PType.FIGHTING,""),
	V_CREATE(180,95,100,0,0,0,PType.FIRE,""),
	VENOM_DRENCH(0,100,0,0,2,0,PType.POISON,""),
	VENOSHOCK(-1,100,0,0,1,0,PType.POISON,""), // TODO
	VISE_GRIP(55,100,0,0,0,0,PType.NORMAL,""),
	VINE_WHIP(45,100,0,0,0,0,PType.GRASS,"A normal attack"),
	VOLT_SWITCH(70,100,0,0,1,0,PType.ELECTRIC,""), // TODO
	//VITAL_THROW(60,1000,0,0,0,0,PType.FIGHTING,"This attack never misses"),
	VOLT_TACKLE(120,100,10,0,0,0,PType.ELECTRIC,"% to Paralyze foe. User takes 1/3 of damage dealt as recoil"),
	WAKE_UP_SLAP(-1,100,0,0,0,0,PType.FIGHTING,"If foe is asleep, power is doubled, but the foe wakes up"),
	WATER_CLAP(0,100,0,0,2,0,PType.WATER,""), // TODO
	WATER_FLICK(0,100,0,0,2,0,PType.WATER,""), // TODO
	WATER_KICK(0,100,0,0,2,0,PType.WATER,""), // TODO
	WATER_SMACK(0,100,0,0,2,0,PType.WATER,""), // TODO
	WATER_SPOUT(-1,100,0,0,1,0,PType.WATER,""), // TODO
	WATER_SPORT(0,1000,0,0,2,0,PType.WATER,""), // TODO
	WAVE_CRASH(120,100,0,0,0,0,PType.WATER,""),
	WATER_GUN(40,100,0,0,1,0,PType.WATER,"A normal attack"),
	//WATER_JET(50,100,0,0,0,1,PType.WATER,"Always attacks first"),
	WATER_PULSE(60,100,30,0,1,0,PType.WATER,"% to Confuse foe"),
	WATERFALL(80,100,10,0,0,0,PType.WATER,"% of causing foe to flinch"),
	WEATHER_BALL(-1,100,0,0,1,0,PType.NORMAL,""), // TODO
	WHIP_SMASH(120,100,0,0,0,0,PType.NORMAL,"A normal attack"),
	WHIRLPOOL(35,85,100,0,1,0,PType.WATER,"% to spin foe for 2-5 turns. While foe is spun, it takes 1/16 HP in damage, and cannot switch"),
	WHIRLWIND(0,1000,0,0,2,-6,PType.WATER,""), // TODO
	WILL_O_WISP(0,80,0,0,2,0,PType.FIRE,"Burns foe"),
	WING_ATTACK(60,100,0,0,0,0,PType.FLYING,"A normal attack"),
	WISH(0,1000,0,0,2,0,PType.NORMAL,""), // TODO
	WITHDRAW(0,1000,0,0,2,0,PType.WATER,""),
	WORRY_SEED(0,100,0,0,2,0,PType.GRASS,""),
	//WOOD_FANG(50,100,50,0,0,0,PType.ROCK,"% to cause foe to flinch"), // recoil
	WRAP(15,90,100,0,0,0,PType.NORMAL,"% to spin foe for 2-5 turns. While foe is spun, it takes 1/16 HP in damage, and cannot switch"),
	//WRING_OUT(-1,100,0,0,0,0,PType.NORMAL,"Attack's power is greater the more HP the foe has"),
	X_SCIZZOR(80,100,0,1,0,0,PType.BUG,"Boosted Crit rate"),
	ZAP_CANNON(120,50,100,0,1,0,PType.ELECTRIC,""),
	//ZAP(20,100,0,0,0,0,PType.ELECTRIC,"A normal attack"),
	ZEN_HEADBUTT(80,90,30,0,0,0,PType.PSYCHIC,"% of causing foe to flinch"),
	ZING_ZAP(80,100,30,0,2,0,PType.ELECTRIC,""),
	
	;
	
	public static Move getMove(String moveName) {
	    for (Move move : Move.values()) {
	        if (move.toString().equalsIgnoreCase(moveName)) {
	            return move;
	        }
	    }
	    return null;
	}

	public int accuracy;
	
	public int basePower;
	
	public int cat;
	
	public int critChance;
	private String desc;
	public PType mtype;
	public int priority;
	public int secondary;
	Move(int bp, int acc, int sec, int crit, int cat, int p, PType type, String desc){
		this.basePower = bp;
		this.accuracy = acc;
		this.secondary = sec;
		this.cat = cat;
		this.critChance = crit;
		this.mtype = type;
		this.priority = p;
		this.desc = desc;
	}
	public String getbp() {
		if (basePower == -1) return "Varies";
		return basePower + "";
	}
	public String getCategory() {
		if (cat == 0) return "Physical";
		if (cat == 1) return "Special";
		else {
			return "Status";
		}
	}

	public String getDescription() {
		if (this.secondary > 0) {
            return secondary + desc;
        } else {
        	return desc;
        }
		
	}
	
	public boolean isAttack() {
		return cat != 2;
	}

	public boolean isPhysical() {
		return cat == 0;
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

}
