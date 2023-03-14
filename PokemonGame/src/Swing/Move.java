package Swing;

public enum Move {
	ABSORB(20,100,0,0,1,false,PType.GRASS,"Heals 50% of damage dealt to foe"),
	ACID(40,100,10,0,1,false,PType.POISON,"% chance to lower foe's Sp.Def by 1"),
	AEROBLAST(100,95,0,1,1,false,PType.FLYING,"Boosted Crit Rate"),
	AGILITY(0,1000,0,0,2,false,PType.FLYING,"Raises user's Speed by 2"),
	AIR_CUTTER(55,95,0,1,1,false,PType.FLYING,"Boosted Crit Rate"),
	AIR_SLASH(75,95,30,0,1,false,PType.FLYING,"% chance of causing foe to flinch"),
	ANCIENT_POWER(60,100,10,0,1,false,PType.ROCK,"% chance to raise all of the user's stats by 1"),
	AQUA_RING(0,1000,0,0,2,false,PType.WATER,"Restores a small amount of HP at the end of every turn"),
	AQUA_TAIL(90,90,0,0,0,false,PType.WATER,"A normal attack"),
	ASSURANCE(50,100,0,0,0,false,PType.DARK,"A normal attack"),
	ASTONISH(30,100,30,0,0,false,PType.GHOST,"% chance of causing foe to flinch"),
	AURA_SPHERE(90,1000,0,0,1,false,PType.FIGHTING,"This attack always hits"),
	AUTOMOTIZE(0,1000,0,0,2,false,PType.STEEL,"Raises user's Speed by 2"),
	AUTO_SHOT(0,1000,0,0,2,false,PType.STEEL,"Causes all of user's \"Shooting\" moves to hit twice"),
	BAWL(0,100,0,0,2,false,PType.DARK,"Lowers foe's Attack by 2"),
	BEAT_UP(60,100,50,0,0,false,PType.DARK,"% chance to cause foe to Bleed"),
	BIG_BULLET(70,90,30,0,0,false,PType.STEEL,"% chance to Paralyze foe"),
	BITE(60,100,30,0,0,false,PType.DARK,"% chance of causing foe to flinch"),
	BLACK_DUST(0,100,0,0,2,false,PType.FIRE,"Lowers foe's Accuracy by 2"),
	BLACK_HOLE(90,90,100,0,1,false,PType.DARK,"% chance of lowering foe's Accuracy by 1"),
	BLAST_BURN(150,90,0,0,1,false,PType.FIRE,"User must rest after using this move"), // recharge
	BLAST_FLAME(100,100,100,0,1,false,PType.FIRE,"% chance to Burn foe"),
	BLAZING_SWORD(90,90,50,0,0,false,PType.FIRE,"% chance to Burn foe"),
	BLIND(0,100,0,0,2,false,PType.NORMAL,"Lowers foe's Accuracy by 2"),
	BLUE_FLARE(150,90,50,0,1,false,PType.FIRE,"% chance to Burn foe"),
	BODY_SLAM(85,100,30,0,0,false,PType.NORMAL,"% chance to Paralyze foe"),
	BOLT_STRIKE(150,90,50,0,1,false,PType.ELECTRIC,"% chance to Paralyze foe"),
	BOULDER_CRUSH(85,80,50,0,0,false,PType.ROCK,"% chance of causing foe to flinch"),
	BOULDER_SLAM(70,100,0,0,0,false,PType.ROCK,"A normal attack"),
	BOUNCE(85,85,30,0,0,false,PType.FLYING,"% chance to Paralyze foe"),
	BRANCH_WHACK(50,95,0,0,0,false,PType.ROCK,"A normal attack"),
	BRAVE_BIRD(120,100,0,0,0,false,PType.FLYING,"User takes 1/3 of damage inflicted"), // recoil
	BRICK_BREAK(75,100,0,0,0,false,PType.FIGHTING,"Breaks Magic Reflect effects"),
	BRINE(-1,100,0,0,1,false,PType.WATER,"Damage is doubled if foe is below 50% HP"),
	BUBBLE(20,100,0,0,1,false,PType.WATER,"A normal attack"),
	BUBBLEBEAM(65,100,10,0,1,false,PType.WATER,"% to lower foe's Speed by 1"),
	BUG_BITE(60,100,0,0,0,false,PType.BUG,"Heals 50% of damage dealt to foe"),
	BUG_BUZZ(90,100,0,0,1,false,PType.BUG,"A normal attack"),
	BULLET_PUNCH(40,100,0,0,0,true,PType.STEEL,"Always goes first"),
	BUZZ(0,100,0,0,2,false,PType.BUG,"Confuses foe"),
	CHARGE(0,1000,0,0,2,false,PType.ELECTRIC,"User's next electric-type attack damage is doubled. Raises user's Sp.Def by 1"),
	CHARM(0,100,0,0,2,false,PType.NORMAL,"Lowers foe's Attack by 2"),
	CHOMP(70,100,30,0,0,false,PType.DARK,"% chance to lower foe's Speed by 1"),
	CLOSE_COMBAT(120,100,100,0,0,false,PType.FIGHTING,"Lowers user's Defense and Sp.Def by 1"),
	COMET_CRASH(-1,90,0,0,0,false,PType.MAGIC,"Damage is doubled if user's HP is full"),
	COMET_PUNCH(-1,85,0,0,0,false,PType.MAGIC,"Attacks 2-5 times"),
	CONFUSE_RAY(0,100,0,0,2,false,PType.GHOST,"Confuses foe"),
	CONFUSION(50,100,10,0,1,false,PType.MAGIC,"% chance to Confuse foe"),
	CONSTRICT(10,100,50,0,0,false,PType.NORMAL,"% chance to lower foe's Speed by 1"),
	CROSS_POISON(70,100,30,1,0,false,PType.POISON,"% chance to Poison foe, boosted Crit rate"),
	CRUNCH(80,100,30,0,0,false,PType.DARK,"% chance to lower foe's Defense by 1"),
	CURSE(0,100,0,0,2,false,PType.GHOST,"User loses half of its total HP. In exchance, foe takes 1/4 of its max HP at the end of every turn"),
	CUT(50,95,0,0,0,false,PType.NORMAL,"A normal attack"),
	DARK_PULSE(80,100,30,0,1,false,PType.DARK,"% chance of causing foe to flinch"),
	DARK_VOID(0,80,0,0,2,false,PType.DARK,"Foe falls asleep"),
	DEFENSE_CURL(0,1000,0,0,2,false,PType.NORMAL,"Raises user's Defense by 1"),
	DESTINY_BOND(0,1000,0,0,2,true,PType.GHOST,"Always goes first; can't be used twice in a row. If foe knocks out user the same turn, foe faints as well"),
	DISAPPEAR(0,1000,50,0,2,false,PType.GHOST,"% chance to Confuse foe; raises user's Evasion by 2"),
	DISCHARGE(80,100,30,0,1,false,PType.ELECTRIC,"% chance to Paralyze foe"),
	DIVE(80,90,0,0,0,false,PType.WATER,"A normal attack"),
	DOUBLE_BLAST(-1,60,30,0,1,false,PType.NORMAL,"% chance to Confuse foe"),
	DOUBLE_HIT(-1,95,0,0,0,false,PType.NORMAL,"Attacks twice"),
	DOUBLE_JET(-1,85,0,0,0,false,PType.WATER,"Attacks 2-5 times"),
	DOUBLE_KICK(-1,85,0,0,0,false,PType.FIGHTING,"Attacks twice"),
	DOUBLE_PUNCH(-1,85,0,0,0,false,PType.FIGHTING,"Attacks twice"),
	DOUBLE_SLAP(-1,95,0,0,0,false,PType.NORMAL,"Attacks 2-5 times"),
	DOUBLE_SLICE(-1,80,15,0,0,false,PType.STEEL,"% to cause foe to Bleed; attacks twice"),
	DOUBLE_TEAM(0,1000,0,0,2,false,PType.NORMAL,"Raises user's Evasion by 1"),
	DOUBLE_EDGE(120,100,0,0,0,false,PType.NORMAL,"User takes 1/3 of damage inflicted"), // recoil
	DRACO_METEOR(140,100,100,0,1,false,PType.DRAGON,"% to lower user's Sp.Atk by 2"),
	DRAGON_CLAW(80,100,0,1,0,false,PType.DRAGON,"Boosted Crit rate"),
	DRAGON_DANCE(0,1000,0,0,2,false,PType.DRAGON,"Raises user's Attack and Speed by 1"),
	DRAGON_PULSE(90,100,0,0,1,false,PType.DRAGON,"A normal attack"),
	DRAGON_RAGE(0,100,0,0,1,false,PType.DRAGON,"Always does 40 HP damage"),
	DRAGON_RUSH(100,75,30,0,0,false,PType.DRAGON,"% chance of causing foe to flinch"),
	DRAGON_BREATH(60,100,30,0,1,false,PType.DRAGON,"% chance to Paralyze foe"),
	DREAM_EATER(100,100,0,0,1,false,PType.DARK,"Only works if target is asleep. Heals 50% of damage dealt to foe"),
	DRILL_PECK(80,100,0,1,0,false,PType.FLYING,"Boosted Crit rate"),
	DRILL_RUN(80,95,0,1,0,false,PType.GROUND,"Boosted Crit rate"),
	DUAL_STAB(-1,80,15,0,0,false,PType.STEEL,"% chance to cause foe to Bleed; attacks twice"),
	EARTH_POWER(90,100,10,0,1,false,PType.GROUND,"% chance to lower foe's Sp.Def by 1"),
	EARTHQUAKE(100,100,0,0,0,false,PType.GROUND,"A normal attack"),
	ELECTROBALL(-1,100,0,0,1,false,PType.ELECTRIC,"Power is higher the faster the user is than the target"),
	ELECTROEXPLOSION(300,100,0,0,1,false,PType.ELECTRIC,"User faints. Bypasses Ground's immunity to Electric"),
	EMBER(40,100,10,0,1,false,PType.FIRE,"% chance to Burn foe"),
	ERUPTION(-1,100,0,0,1,false,PType.FIRE,"Power is higher the more HP the user has"),
	EXPLOSION(250,100,0,0,0,false,PType.NORMAL,"User faints"),
	EXTRAORDINARY(-1,1000,0,0,1,false,PType.MAGIC,"Selects a random move!"),
	EXTREME_SPEED(70,100,0,0,0,false,PType.FLYING,"Always goes first"),
	FAINT_ATTACK(60,1000,0,0,0,false,PType.DARK,"This attack always hits"),
	FALSE_SWIPE(40,100,0,0,0,false,PType.BUG,"Always leaves the foe with at least 1 HP"),
	FIREBALL(-1,100,10,0,1,false,PType.FIRE,"% chance to Burn foe, damage is doubled if foe is Burned"),
	FIRE_BLAST(120,85,10,0,1,false,PType.FIRE,"% chance to Burn foe"),
	FIRE_CHARGE(75,90,10,0,0,false,PType.FIRE,"% of flinching and/or Burning foe"),
	FIRE_DASH(0,100,100,0,0,false,PType.FIRE,"% to Burn foe, user faints. Damage equals user's remaining HP"),
	FIRE_FANG(65,95,10,0,0,false,PType.FIRE,"% of flinching and/or Burning foe"),
	FIRE_PUNCH(75,100,10,0,0,false,PType.FIRE,"% to Burn foe"),
	FIRE_SPIN(35,85,100,0,1,false,PType.FIRE,"% to spin foe for 2-5 turns. While foe is spun, it takes 1/16 HP in damage, and cannot switch"),
	FIRE_TAIL(85,90,10,0,0,false,PType.FIRE,"% to Burn foe"),
	FLAIL(-1,100,0,0,0,false,PType.NORMAL,"Power is higher the lower HP the user has"),
	FLAME_BURST(110,100,100,0,1,false,PType.FIRE,"% to inflict burn, but user becomes Confused"),
	FLAME_WHEEL(60,100,10,0,0,false,PType.FIRE,"% to Burn foe"),
	FLAMETHROWER(90,100,10,0,1,false,PType.FIRE,"% to Burn foe"),
	FLARE_BLITZ(120,100,10,0,0,false,PType.FIRE,"% to Burn foe, user takes 1/3 of damage inflicted"), // recoil
	FLASH(0,100,0,0,2,false,PType.NORMAL,"Lowers foe's Accuracy by 1"),
	FLASH_CANNON(80,100,50,0,1,false,PType.STEEL,"% chance to lower foe's Sp.Def by 1"),
	FLY(90,100,0,0,0,false,PType.FLYING,"A normal attack"),
	FORESIGHT(0,1000,0,0,2,false,PType.MAGIC,"Indentifies foe, replacing their Ghost typing with Normal if they have it. It also raises user's Accuracy by 1 stage"),
	FRENZY_PLANT(150,90,0,0,1,false,PType.GRASS,"User must rest after using this move"), // recharge
	FRUSTERATION(102,100,0,0,0,false,PType.NORMAL,"A normal attack"),
	FURY_ATTACK(-1,85,0,0,0,false,PType.NORMAL,"Attacks 2-5 times"),
	FURY_CUTTER(-1,95,0,0,0,false,PType.BUG,"Power increases the more times this move is used in succession"),
	FURY_SWIPES(-1,80,0,0,0,false,PType.NORMAL,"Attacks 2-5 times"),
	GALAXY_ATTACK(115,90,30,0,0,false,PType.MAGIC,"% chance to inflict the foe with a random Status condition"),
	GIGA_DRAIN(75,100,0,0,1,false,PType.GRASS,"Heals 50% of damage dealt to foe"),
	GIGA_HIT(110,75,50,0,0,false,PType.FIGHTING,"% chance to Paralyze foe"),
	GIGA_IMPACT(150,90,0,0,0,false,PType.NORMAL,"User must rest after using this move"), // recharge
	GLARE(0,100,0,0,2,false,PType.DARK,"Paralyzes foe"),
	GRASS_KNOT(80,100,0,0,1,false,PType.GRASS,"A normal attack"),
	GRASS_PUNCH(80,100,0,0,0,false,PType.GRASS,"A normal attack"),
	GROWL(0,100,0,0,2,false,PType.NORMAL,"Lowers foe's Attack by 1"),
	GROWTH(0,1000,0,0,2,false,PType.GRASS,"Raises user's Attack and Sp.Atk by 1"),
	GUNK_SHOT(120,70,30,0,1,false,PType.POISON,"% chance to Poison foe"),
	GUNSHOT(70,60,0,2,0,false,PType.STEEL,"25% chance to Crit. If it Crits, foe is Bleeding"),
	GUST(40,100,0,0,1,false,PType.FLYING,"A normal attack"),
	GYRO_BALL(-1,100,0,0,0,false,PType.STEEL,"The lower the user's speed compared to the foe, the more power"),
	HAMMER_ARM(100,90,100,0,0,false,PType.FIGHTING,"% chance to lower user's speed by 1"),
	HARDEN(0,1000,0,0,2,false,PType.NORMAL,"Raises user's Defense by 1"),
	HAZE(0,1000,0,0,2,false,PType.GHOST,"Clears all stat changes on the field"),
	HEAD_SMASH(150,80,0,0,0,false,PType.ROCK,"User takes 1/3 of damage inflicted"), // recoil
	HEADBUTT(70,100,30,0,0,false,PType.NORMAL,"% chance of causing foe to flinch"),
	HEAT_WAVE(100,90,10,0,1,false,PType.FIRE,"% to Burn foe"),
	HI_JUMP_KICK(130,90,0,0,0,false,PType.FIGHTING,"If this attack misses, user takes 50% of its max HP"),
	HORN_ATTACK(65,100,0,0,0,false,PType.NORMAL,"A normal attack"),
	HORN_DRILL(0,30,0,0,0,false,PType.NORMAL,"If this move hits, it always K.Os foe"),
	HYDRO_CANNON(150,90,0,0,1,false,PType.WATER,"User must rest after using this move"), // recharge
	HYDRO_PUMP(120,80,0,0,1,false,PType.WATER,"A normal attack"),
	HYPER_BEAM(150,90,0,0,1,false,PType.NORMAL,"User must rest after using this move"), // recharge
	HYPER_FANG(80,90,10,0,0,false,PType.NORMAL,"% of causing foe to flinch"),
	HYPNOSIS(0,60,0,0,2,false,PType.GHOST,"Causes foe to sleep"),
	IGNITE(0,75,0,0,2,false,PType.FIRE,"Burns foe"),
	INJECT(55,100,100,0,0,false,PType.BUG,"% to Poison foe, heals 50% of damage dealt"),
	IRON_DEFENSE(0,1000,0,0,2,false,PType.STEEL,"Raises user's Defense by 2"),
	IRON_HEAD(80,100,30,0,0,false,PType.STEEL,"% of causing foe to flinch"),
	IRON_TAIL(100,75,30,0,0,false,PType.STEEL,"% of lowering foe's Defense by 1"),
	KARATE_CHOP(50,100,0,1,0,true,PType.FIGHTING,"Boosted Crit rate"),
	LAVA_PLUME(80,100,30,0,1,false,PType.FIRE,"% to Burn foe"),
	LEAF_BALL(75,95,0,0,1,false,PType.GRASS,"A normal attack"),
	LEAF_BLADE(90,100,0,1,0,false,PType.GRASS,"Boosted Crit rate"),
	LEAF_GUST(50,100,0,0,1,false,PType.GRASS,"A normal attack"),
	LEAF_KOBE(75,90,100,0,0,false,PType.GRASS,"% to Paralyze foe"),
	LEAF_PULSE(85,75,100,0,1,false,PType.GRASS,"% to lower foe's accuracy, 50% to cause foe to fall asleep"),
	LEAF_SLAP(30,100,0,0,0,false,PType.GRASS,"A normal attack"),
	LEAF_STORM(140,90,100,0,1,false,PType.GRASS,"% to lower user's Sp.Atk by 2"),
	LEAF_TORNADO(65,90,50,0,1,false,PType.GRASS,"% to lower foe's Accuracy by 1"),
	LEECH_LIFE(20,100,0,0,0,false,PType.BUG,"Heals 50% of damage dealt"),
	LEECH_SEED(0,90,0,0,2,false,PType.GRASS,"At the end of every turn, user steals 1/8 of foe's max HP"),
	LEER(0,100,0,0,2,false,PType.NORMAL,"Lowers foe's Defense by 1"),
	LICK(20,100,30,0,0,false,PType.GHOST,"% to Paralyze foe"),
	LIGHTNING_HEADBUTT(90,95,30,0,0,false,PType.ELECTRIC,"% of Paralysis and/or causing foe to flinch. User takes 1/3 of damage dealt as recoil"), // recoil
	LOCK_ON(0,1000,0,0,2,false,PType.STEEL,"Raises user's Accuracy by 6"),
	LOW_KICK(60,100,100,0,0,false,PType.FIGHTING,"% to lower foe's Speed by 1"),
	MACH_PUNCH(40,100,0,0,0,true,PType.FIGHTING,"Always goes first"),
	MACHETE_STAB(75,80,100,0,0,false,PType.STEEL,"% to cause foe to Bleed"),
	MAGIC_BLAST(30,100,0,0,1,false,PType.MAGIC,"A random Rock, Ground or Grass move is also used"),
	MAGIC_CRASH(150,90,100,0,0,false,PType.MAGIC,"% to inflict foe with a random Status condition. User must rest after using"), // recharge
	MAGIC_FANG(70,95,75,0,0,false,PType.MAGIC,"% to flinch foe if this move is Super-Effective against it"),
	MAGIC_REFLECT(0,1000,0,0,2,false,PType.MAGIC,"Foe's next attack will be reflected against them. Can be used every other turn"),
	MAGIC_TOMB(90,100,0,0,1,false,PType.MAGIC,"A normal attack"),
	MAGICAL_LEAF(60,1000,0,0,1,false,PType.GRASS,"This move will never miss"),
	MAGNET_RISE(0,1000,0,0,2,false,PType.STEEL,"User will float for 5 turns, causing it to be immune to all Ground-type attacks"),
	MAGNITUDE(-1,100,0,0,0,false,PType.GROUND,"A random Magnitude between 4-10 will be used, corresponding to its power"),
	MEGA_DRAIN(40,100,0,0,1,false,PType.GRASS,"Heals 50% of damage dealt"),
	MEGA_KICK(70,100,60,0,0,false,PType.FIGHTING,"% to Paralyze foe"),
	MEGA_PUNCH(70,100,60,0,0,false,PType.FIGHTING,"% to Paralyze foe"),
	MEGA_SWORD(70,100,60,0,0,false,PType.STEEL,"% to Paralyze foe"),
	METAL_SOUND(0,100,0,0,2,false,PType.STEEL,"Lowers foe's Sp.Def by 2"),
	MINIMIZE(0,1000,0,0,2,false,PType.GHOST,"Raises user's Evasion by 2"),
	MIRROR_MOVE(0,1000,0,0,1,false,PType.FLYING,"Uses the move last used by the foe, fails if foe hasn't used a move yet"),
	MOONLIGHT(0,1000,0,0,2,false,PType.MAGIC,"Restores 1/2 of user's max HP"),
	MUD_BOMB(65,85,30,0,1,false,PType.GROUND,"% to lower foe's Accuracy by 1"),
	MUD_SLAP(20,100,100,0,1,false,PType.GROUND,"% to lower foe's Accuracy by 1"),
	MUD_SPORT(0,1000,0,0,2,false,PType.GROUND,"Does nothing"),
	NEEDLE_SPRAY(55,95,10,0,0,false,PType.POISON,"% to Poison or Paralyze foe"),
	NIBBLE(10,100,0,0,0,false,PType.NORMAL,"A normal attack"),
	NIGHT_SHADE(0,100,0,0,1,false,PType.GHOST,"Deals damage equal to user's level"),
	NIGHT_SLASH(70,100,0,1,0,false,PType.DARK,"Boosted Crit rate"),
	NIGHTMARE(0,100,0,0,2,false,PType.GHOST,"Foe loses 1/4 of max HP each turn; wears off when foe wakes up"),
	ODOR_SLEUTH(0,1000,0,0,2,false,PType.NORMAL,"Indentifies foe, replacing their Ghost typing with Normal if they have it. It also lowers foe's Evasion by 1"),
	OUTRAGE(120,100,0,0,0,false,PType.DRAGON,"User is locked into this move for 2-3 turns, Confuses user when the effect is done"),
	OVERHEAT(140,90,100,0,1,false,PType.FIRE,"% to lower user's Sp.Atk by 2"),
	PECK(35,100,0,0,0,false,PType.FLYING,"A normal attack"),
	PERISH_SONG(0,1000,0,0,2,false,PType.GHOST,"All Pokemon hearing this song will faint in 3 turns"),
	PHASE_SHIFT(0,1000,0,0,2,false,PType.MAGIC,"Switches user's type to Magic and the type of the move that the foe just used"),
	POISON_BALL(65,80,100,0,0,false,PType.POISON,"% to Poison foe"),
	POISON_FANG(50,100,30,0,0,false,PType.POISON,"% to Poison and/or flinch foe"),
	POISON_GAS(0,80,0,0,2,false,PType.POISON,"Poisons foe"),
	POISON_JAB(80,100,30,0,0,false,PType.POISON,"% to Poison foe"),
	POISON_POWDER(0,75,0,0,2,false,PType.POISON,"Poisons foe"),
	POISON_PUNCH(75,100,10,0,0,false,PType.POISON,"% chance to Poison foe"),
	POISON_STING(15,100,30,0,0,false,PType.POISON,"% chance to Poison foe"),
	POISONOUS_WATER(95,85,30,0,1,false,PType.POISON,"% chance to Poison foe"),
	POKE(10,100,0,0,0,false,PType.NORMAL,"A normal attack"),
	POUND(40,100,0,0,0,false,PType.NORMAL,"A normal attack"),
	SHELL_SMASH(0,1000,0,0,2,true,PType.NORMAL,"Raises user's Attack, Sp.Atk, and Speed by 2, at the cost of lowering its Defense and Sp.Def by 1"),
	PSYCHO_BLADE(70,100,0,1,0,false,PType.MAGIC,"Boosted Crit rate"),
	PUNCH(40,90,0,0,0,false,PType.FIGHTING,"A normal attack"),
	PURSUIT(40,100,0,0,0,false,PType.DARK,"A normal attack"),
	QUICK_ATTACK(40,100,0,0,0,true,PType.NORMAL,"Always attacks first"),
	RAGE(-1,100,0,0,0,false,PType.NORMAL,"Power increases the more times this move is used in succession"),
	RAPID_SPIN(20,100,100,0,0,false,PType.NORMAL,"% to raise user's Speed by 1, and frees user of being Spun"),
	RAZOR_LEAF(55,95,0,1,0,false,PType.GRASS,"Boosted Crit rate"),
	REBOOT(0,1000,0,0,2,false,PType.STEEL,"Clears user or any Status condition, and raises user's Speed by 1"),
	REVENGE(-1,100,0,0,0,false,PType.FIGHTING,"Power is doubled if user is slower than foe"),
	ROCK_BLADE(80,100,0,1,0,false,PType.ROCK,"Boosted Crit rate"),
	ROCK_BLAST(-1,90,0,0,0,false,PType.ROCK,"Hits 2-5 times"),
	ROCK_SMASH(40,100,50,0,0,false,PType.FIGHTING,"% to lower foe's Defense by 1"),
	ROCK_THROW(50,90,0,0,0,false,PType.ROCK,"A normal attack"),
	ROCK_TOMB(60,80,100,0,0,false,PType.ROCK,"% to lower foe's Speed by 1"),
	ROCKET(120,75,50,0,0,false,PType.STEEL,"% to Paralyze foe"),
	ROLLOUT(-1,90,0,0,0,false,PType.ROCK,"Attacks up to 5 times, damage doubles each time. While active, user cannot switch out"),
	ROOST(0,1000,0,0,2,false,PType.FLYING,"Restores 1/2 of user's max HP"),
	ROOT_CRUSH(105,90,100,0,0,false,PType.ROCK,"% to Paralyze foe"),
	ROOT_KICK(60,95,0,0,0,false,PType.ROCK,"A normal attack"),
	SAND_ATTACK(0,100,0,0,2,false,PType.GROUND,"Lowers foe's Accuracy by 1"),
	SCARY_FACE(0,100,0,0,2,false,PType.NORMAL,"Lowers foe's Speed by 2"),
	SCRATCH(40,100,0,0,0,false,PType.NORMAL,"A normal attack"),
	SCREECH(0,85,0,0,2,false,PType.NORMAL,"Lowers foe's Defense by 2"),
	SEISMIC_TOSS(0,100,0,0,0,false,PType.FIGHTING,"Damage dealt is equal to the user's level"),
	SELF_DESTRUCT(200,100,0,0,0,false,PType.NORMAL,"User faints"),
	SHADOW_BALL(80,100,30,0,1,false,PType.GHOST,"% to lower foe's Sp.Def by 1"),
	SHADOW_SNEAK(40,100,0,0,0,true,PType.GHOST,"Always attacks first"),
	SHELL_BASH(70,100,0,1,0,false,PType.NORMAL,"User takes 1/3 of damage dealt as recoil"), // recoil
	SHOCK(15,100,100,0,1,false,PType.ELECTRIC,"% to Paralyze foe"),
	SHOCK_WAVE(60,1000,0,0,1,false,PType.ELECTRIC,"This attack never misses"),
	SHURIKEN(75,85,50,0,0,false,PType.STEEL,"% to cause foe to Bleed"),
	SKULL_BASH(100,100,100,0,0,false,PType.NORMAL,"% to raise the user's Defense by 1, user must charge on the first turn"), // charge
	SKY_ATTACK(140,90,30,1,0,false,PType.FLYING,"% chance to flinch. User must charge up on the first turn, attacks on the second. Boosted Crit rate"), // charge
	SKY_UPPERCUT(85,90,0,0,0,false,PType.FIGHTING,"A normal attack"),
	SLAM(80,75,0,0,0,false,PType.NORMAL,"A normal attack"),
	SLAP(20,100,0,0,0,false,PType.NORMAL,"A normal attack"),
	SLASH(70,100,0,1,0,false,PType.NORMAL,"Boosted Crit rate"),
	SLEEP_POWDER(0,75,0,0,2,false,PType.GRASS,"Foe falls asleep"),
	SLUDGE(65,100,30,0,1,false,PType.POISON,"% to Poison foe"),
	SLUDGE_BOMB(90,100,30,0,1,false,PType.POISON,"% to Poison foe"),
	SMASH(70,90,0,0,0,false,PType.NORMAL,"A normal attack"),
	SMOG(20,70,50,0,1,false,PType.POISON,"% to Poison foe"),
	SMOKESCREEN(0,100,0,0,2,false,PType.NORMAL,"Lowers foe's accuracy by 1"),
	SOLAR_BEAM(120,100,0,0,1,false,PType.GRASS,"User must charge up on the first turn, attacks on the second"), // charge
	SPARK(65,100,30,0,0,false,PType.ELECTRIC,"% to Paralyze foe"),
	SPIKE_JAB(55,80,100,0,0,false,PType.POISON,"% to Poison foe"),
	SPIKE_SHOT(-1,100,0,0,0,false,PType.POISON,"Attacks 2-5 times"),
	SPIKE_SLAM(65,90,0,0,0,false,PType.NORMAL,"A normal attack"),
	STAR_STORM(110,85,0,0,1,false,PType.MAGIC,"A normal attack"),
	STARE(0,100,0,0,2,false,PType.NORMAL,"Confuses foe, but raises foe's Attack by 1"),
	STING(55,100,100,0,0,false,PType.BUG,"% to make foe Bleed"),
	STOMP(65,100,30,0,0,false,PType.NORMAL,"% of causing foe to flinch"),
	STONE_EDGE(100,80,0,1,0,false,PType.ROCK,"Boosted Crit rate"),
	STRENGTH(80,100,0,0,0,false,PType.NORMAL,"A normal attack"),
	STRING_SHOT(0,100,0,0,2,false,PType.BUG,"Lowers foe's Speed by 2"),
	STRONG_ARM(90,85,30,0,0,false,PType.FIGHTING,"% chance of Paralyzing and/or causing foe to flinch"),
	SUCKER_PUNCH(80,100,0,0,0,true,PType.DARK,"Always attacks first. Fails if foe didn't use an attacking move"),
	SUPER_CHARGE(90,50,100,0,0,false,PType.ELECTRIC,"% of causing foe to flinch, user takes 1/3 of damage dealt as recoil"), // recoil
	SUPER_FANG(0,100,0,0,0,false,PType.NORMAL,"Halves foe's remaining HP"),
	SUPERPOWER(120,100,100,0,0,false,PType.FIGHTING,"% of lowering user's Attack and Defense by 1"),
	SUPERSONIC(0,55,0,0,2,false,PType.NORMAL,"Confuses foe"),
	SURF(95,100,0,0,1,false,PType.WATER,"A normal attack"),
	SWAGGER(0,85,0,0,2,false,PType.NORMAL,"Confuses foe, but raises foe's Attack by 2"),
	SWEEP_KICK(60,95,100,0,0,false,PType.FIGHTING,"% to lower foe's Attack by 1"),
	SWIFT(60,1000,0,0,1,false,PType.MAGIC,"This attack never misses"),
	SWORD_SLASH(75,90,0,1,0,false,PType.STEEL,"Boosted Crit rate"),
	SWORD_SLICE(65,85,0,1,0,false,PType.STEEL,"Boosted Crit rate. If it Crits, foe is Bleeding"),
	SWORD_SPIN(50,95,100,0,0,false,PType.STEEL,"% to raise user's Attack by 1"),
	SWORD_STAB(95,60,100,0,0,false,PType.STEEL,"% to cause foe to Bleed"),
	SYNTHESIS(0,1000,0,0,2,false,PType.GRASS,"Restores 1/2 of user's max HP"),
	TACKLE(50,100,0,0,0,false,PType.NORMAL,"Really? You're looking what tackle does?"),
	TAIL_WHACK(90,85,0,0,0,false,PType.NORMAL,"A normal attack"),
	TAIL_WHIP(0,100,0,0,2,false,PType.NORMAL,"Lowers foe's Defense by 1"),
	TAKE_DOWN(90,85,0,0,0,false,PType.NORMAL,"User takes 1/3 of damage dealt as recoil"), // recoil
	TAKE_OVER(0,100,0,0,2,false,PType.GHOST,"Foe's next attack is used on itself. Can be used once every other turn"),
	THUNDER(120,70,30,0,1,false,PType.ELECTRIC,"% of Paralyzing foe"),
	THUNDERBOLT(95,100,10,0,1,false,PType.ELECTRIC,"% of Paralyzing foe"),
	THUNDER_FANG(65,95,10,0,0,false,PType.ELECTRIC,"% of Paralyzing and/or flinching foe"),
	THUNDER_KICK(80,90,10,0,0,false,PType.ELECTRIC,"% of Paralyzing foe"),
	THUNDER_PUNCH(75,100,10,0,0,false,PType.ELECTRIC,"% of Paralyzing foe"),
	THUNDERSHOCK(40,100,10,0,1,false,PType.ELECTRIC,"% of Paralyzing foe"),
	THUNDER_WAVE(0,100,0,0,2,false,PType.ELECTRIC,"Paralyzes foe"),
	TIDAL_WAVE(-1,100,0,0,1,false,PType.WATER,"Picks a random tide level from the time of day. Morning = 90, Day = 50, and Evening = 130 Base Power"),
	TORNADO_SPIN(60,95,100,0,0,false,PType.FIGHTING,"% to raise user's Speed and Accuracy by 1, and frees user of being Spun"),
	TOXIC(0,100,0,0,2,false,PType.POISON,"Poisons foe"),
	TWISTER(40,100,10,0,1,false,PType.DRAGON,"% of causing foe to flinch"),
	FIRST_IMPRESSION(90,100,0,0,0,true,PType.BUG,"Always attacks first, fails after the first turn a user is out in battle"),
	VINE_WHIP(35,100,0,0,0,false,PType.GRASS,"A normal attack"),
	VITAL_THROW(60,1000,0,0,0,false,PType.FIGHTING,"This attack never misses"),
	VOLT_TACKLE(120,100,10,0,0,false,PType.ELECTRIC,"% to Paralyze foe. User takes 1/3 of damage dealt as recoil"), // recoil
	WAKE_UP_SLAP(-1,100,0,0,0,false,PType.FIGHTING,"If foe is asleep, power is doubled, but the foe wakes up"),
	WATER_GUN(40,100,0,0,1,false,PType.WATER,"A normal attack"),
	WATER_JET(50,100,0,0,0,true,PType.WATER,"Always attacks first"),
	WATER_PULSE(60,100,30,0,1,false,PType.WATER,"% to Confuse foe"),
	WATERFALL(80,100,10,0,0,false,PType.WATER,"% of causing foe to flinch"),
	WHIP_SMASH(120,100,0,0,0,false,PType.NORMAL,"A normal attack"),
	WHIRLPOOL(35,85,100,0,1,false,PType.WATER,"% to spin foe for 2-5 turns. While foe is spun, it takes 1/16 HP in damage, and cannot switch"),
	WILL_O_WISP(0,80,0,0,2,false,PType.FIRE,"Burns foe"),
	WING_ATTACK(60,100,0,0,0,false,PType.FLYING,"A normal attack"),
	WOOD_FANG(50,100,50,0,0,false,PType.ROCK,"% to cause foe to flinch"),
	WRAP(15,90,100,0,0,false,PType.NORMAL,"% to spin foe for 2-5 turns. While foe is spun, it takes 1/16 HP in damage, and cannot switch"),
	WRING_OUT(-1,100,0,0,0,false,PType.NORMAL,"Attack's power is greater the more HP the foe has"),
	X_SCIZZOR(80,100,0,1,0,false,PType.BUG,"Boosted Crit rate"),
	ZAP(20,100,0,0,0,false,PType.ELECTRIC,"A normal attack"),
	ZEN_HEADBUTT(80,90,30,0,0,false,PType.MAGIC,"% of causing foe to flinch"),
	ROCK_SLIDE(75,90,30,0,0,false,PType.ROCK,"% of causing foe to flinch"),
	ROCK_POLISH(0,1000,0,0,2,false,PType.ROCK,"Raises user's Speed by 2"),
	ROCK_WRECKER(150,90,0,0,0,false,PType.ROCK,"User takes 1/3 of damage dealt as recoil"), // recoil
	FAILED_SUCKER(0,100,0,0,0,false,PType.DARK,"If you're seeing this, something went horribly wrong"), 
	BULK_UP(0,1000,0,0,2,false,PType.FIGHTING,"Raises user's Attack and Defense by 1"),
	CALM_MIND(0,1000,0,0,2,false,PType.MAGIC,"Raises user's Sp.Atk and Sp.Def by 1"),
	SWORDS_DANCE(0,1000,0,0,2,false,PType.NORMAL,"Raises user's Attack by 2"),
	
	;
	
	Move(int bp, int acc, int sec, int crit, int cat, boolean p, PType type, String desc){
		this.basePower = bp;
		this.accuracy = acc;
		this.secondary = sec;
		this.cat = cat;
		this.critChance = crit;
		this.mtype = type;
		this.priority = p;
		this.desc = desc;
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
	private String desc;
	
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
	
	public String getDescription() {
		if (this.secondary > 0) {
            return secondary + desc;
        } else {
        	return desc;
        }
		
	}

	public String getCategory() {
		if (cat == 0) return "Physical";
		if (cat == 1) return "Special";
		else {
			return "Status";
		}
	}
	
	public String getbp() {
		if (basePower == -1) return "Varies";
		return basePower + "";
	}

}
