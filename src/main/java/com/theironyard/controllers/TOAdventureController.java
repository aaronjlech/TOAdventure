package com.theironyard.controllers;

import com.theironyard.entities.*;
import com.theironyard.entities.Character;
import com.theironyard.services.*;
import com.theironyard.utlities.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by michaelplott on 11/10/16.
 */
@RestController
public class TOAdventureController {
    @Autowired
    UserRepo users;

    @Autowired
    AvatarRepo avatars;

    @Autowired
    NPCRepo npcs;

    @Autowired
    CharacterRepo characters;

    @Autowired
    UserSpriteRepo usersprites;

    @Autowired
    UserItemRepo useritems;

    @Autowired
    BossRepo bosses;

    @Autowired
    BossAssetRepo bossassets;

    Server h2;

    @PostConstruct
    public void init() throws SQLException, PasswordStorage.CannotPerformOperationException {
        h2.createWebServer().start();

        if (users.count() == 0) {
            users.save(new User("mike", PasswordStorage.createHash("123")));
            users.save(new User("sam", PasswordStorage.createHash("123")));
            users.save(new User("tom", PasswordStorage.createHash("123")));
            users.save(new User("rob", PasswordStorage.createHash("123")));
            users.save(new User("nick", PasswordStorage.createHash("123")));
        }

        if (characters.count() == 0) {
            User user = users.findFirstByUsername("mike");
            User user1 = users.findFirstByUsername("sam");
            User user2 = users.findFirstByUsername("tom");
            User user3 = users.findFirstByUsername("rob");
            User user4 = users.findFirstByUsername("nick");
            characters.save(new Character("avatars/human-standing.png", "avatars/human-jumping.png", "avatars/human-death.png", 0, 12, 0, 0, user));
            characters.save(new Character("avatars/elf-standing.png", "avatars/elf-jumping.png", "avatars/elf-death.png", 0, 143, 0, 0, user1));
            characters.save(new Character("avatars/mister-t-standing.png", "avatars/mister-t-jumping.png", "avatars/mister-t-death.png", 0, 1235, 0, 0, user2));
            characters.save(new Character("avatars/orc-standing.png", "avatars/orc-jumping.png", "avatars/orc-death.png", 0, 1234123, 0, 0, user3));
            characters.save(new Character("avatars/skeleton-standing.png", "avatars/skeleton-jumping.png", "avatars/skeleton-death.png", 0, 13422141, 0, 0, user4));
        }

        if (avatars.count() == 0) {
            avatars.save(new Avatar("avatars/human-standing.png", Avatar.Animation.STANDING, Avatar.Race.HUMAN));
            avatars.save(new Avatar("avatars/human-jumping.png", Avatar.Animation.JUMPING, Avatar.Race.HUMAN));
            avatars.save(new Avatar("avatars/human-death.png", Avatar.Animation.DEATH, Avatar.Race.HUMAN));
            avatars.save(new Avatar("avatars/elf-standing.png", Avatar.Animation.STANDING, Avatar.Race.ELF));
            avatars.save(new Avatar("avatars/elf-jumping.png", Avatar.Animation.JUMPING, Avatar.Race.ELF));
            avatars.save(new Avatar("avatars/elf-death.png", Avatar.Animation.DEATH, Avatar.Race.ELF));
            avatars.save(new Avatar("avatars/mister-t-standing.png", Avatar.Animation.STANDING, Avatar.Race.MISTER_T));
            avatars.save(new Avatar("avatars/mister-t-jumping.png", Avatar.Animation.JUMPING, Avatar.Race.MISTER_T));
            avatars.save(new Avatar("avatars/mister-t-death.png", Avatar.Animation.DEATH, Avatar.Race.MISTER_T));
            avatars.save(new Avatar("avatars/orc-standing.png", Avatar.Animation.STANDING, Avatar.Race.ORC));
            avatars.save(new Avatar("avatars/orc-jumping.png", Avatar.Animation.JUMPING, Avatar.Race.ORC));
            avatars.save(new Avatar("avatars/orc-death.png", Avatar.Animation.DEATH, Avatar.Race.ORC));
            avatars.save(new Avatar("avatars/skeleton-standing.png", Avatar.Animation.STANDING, Avatar.Race.SKELETON));
            avatars.save(new Avatar("avatars/skeleton-jumping.png", Avatar.Animation.JUMPING, Avatar.Race.SKELETON));
            avatars.save(new Avatar("avatars/skeleton-death.png", Avatar.Animation.DEATH, Avatar.Race.SKELETON));
        }

        if (npcs.count() == 0) {
            npcs.save(new NPC("npcs/enemy1.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy2.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy3.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy4.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy5.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy6.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy7.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy8.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy9.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy10.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy11.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy12.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy13.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy14.png", NPC.Category.ENEMY));
            npcs.save(new NPC("npcs/enemy15.png", NPC.Category.ENEMY));
            npcs.save(new NPC("money/money.png", NPC.Category.MONEY));
            npcs.save(new NPC("items/axe.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/billyclub.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/hammer.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/hatchet.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/paddle.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/pickaxe.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/scimitar.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/staff.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/staff2.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/stonespear.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/torch.png", NPC.Category.ITEM));
            npcs.save(new NPC("items/trident.png", NPC.Category.ITEM));
            npcs.save(new NPC("health/health.png", NPC.Category.HEALTH));
        }

        if (bosses.count() == 0) {
            bosses.save(new Boss("bosses/mage-standing.png"));
        }

        if (bossassets.count() == 0) {
            Boss boss = bosses.findOne(1);
            bossassets.save(new BossAsset("bossassets/mage-bullet1.png", boss));
            bossassets.save(new BossAsset("bossassets/mage-bullet2.png", boss));
            bossassets.save(new BossAsset("bossassets/mage-bullet3.png", boss));
            bossassets.save(new BossAsset("bossassets/mage-bullet4.png", boss));
            bossassets.save(new BossAsset("bossassets/mage-bullet5.png", boss));
        }

        if (useritems.count() == 0) {
            User user = users.findFirstByUsername("mike");
            useritems.save(new Item("items/axe.png", user));
            useritems.save(new Item("items/hammer.png", user));
            useritems.save(new Item("items/stonespear.png", user));
        }
    }

    @PreDestroy
    public void destroy() {
        h2.stop();
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Character postUser(HttpSession session, @RequestBody User user) throws Exception {
        User userFromDb = users.findFirstByUsername(user.getUsername());
        if (userFromDb == null) {
            throw new Exception("User not found.");
            //return new ResponseEntity<Character>(HttpStatus.NOT_FOUND);
            //user.setPassword(PasswordStorage.createHash(user.getPassword()));
            //users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(user.getPassword(), userFromDb.getPassword())) {
            throw new Exception("Invalid password!");
        }

        session.setAttribute("username", user.getUsername());
        return characters.findByUser(userFromDb);
    }



    // route allowing users to login to the site and returns a user object to the client.

//    @RequestMapping(path = "/login", method = RequestMethod.POST)
//    public ResponseEntity<User> postUser(HttpSession session, @RequestBody User user) throws PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException {
//        User userFromDb = users.findFirstByUsername(user.getUsername());
//        if (userFromDb == null) {
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//            //user.setPassword(PasswordStorage.createHash(user.getPassword()));
//            //users.save(user);
//        }
//        else if (!PasswordStorage.verifyPassword(user.getPassword(), userFromDb.getPassword())) {
//            return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
//        }
//
//        session.setAttribute("username", user.getUsername());
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }

    // route gets avatars that the user can then choose from, this avatar object is expected back in the post route.

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public Iterable<Avatar> getAvatars() {
        return avatars.findByAnimation(Avatar.Animation.STANDING);
    }

    // route receives a map of json key value pairs. keys are "username", "password" and "filename".
    // the user is created with the value of keys username and password and then the character is created by
    // getting both avatars that are paired with the filename of what is coming back.

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public Character getUser(HttpSession session, @RequestBody Map<String, String> json) throws PasswordStorage.CannotPerformOperationException {
        if (json.get("username") == null) {
            return null;
        }
        User user = new User(json.get("username"), PasswordStorage.createHash(json.get("password")));
        //user.setPassword(PasswordStorage.createHash(user.getPassword()));
        users.save(user);
        Avatar avatar1 = avatars.findByFilename(json.get("filename"));
        Avatar avatar2 = avatars.findOne(avatar1.getId() + 1);
        Avatar avatar3 = avatars.findOne(avatar2.getId() + 1);
        Character character = new Character(avatar1.getFilename(), avatar2.getFilename(), avatar3.getFilename(), 0, 0, 0, 0, user);
        characters.save(character);
        session.setAttribute("username", user.getUsername());
        return character;
    }

    // route recieves a user object and hashes the password and saves it to the database as well as creating a new character object
    // setting the default values and saving that to the database. returns a user object.

//    @RequestMapping(path = "/signup", method = RequestMethod.POST)
//    public Character getUser(HttpSession session, @RequestBody User user, @RequestBody Avatar avatar) throws PasswordStorage.CannotPerformOperationException {
//        if (user == null) {
//            return null;
//        }
//        user.setPassword(PasswordStorage.createHash(user.getPassword()));
//        users.save(user);
//        Avatar avatar1 = avatars.findByFilename(avatar.getFilename());
//        Avatar avatar2 = avatars.findOne(avatar1.getId() + 1);
//        Character character = new Character(avatar.getFilename(), avatar2.getFilename(), 0, 0, 0, 0, user);
//        characters.save(character);
//        session.setAttribute("username", user.getUsername());
//        return character;
//    }

    // route returns a user object to the client.

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User getUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return users.findFirstByUsername(username);
    }

    // route expecting a map of key value pairs, keys expected are as follows. "id", "score". I pull the character from the DB
    // using the id field of the character. then I set the score of that character object from the DB to the value of score from
    // the front end. Then the object is saved to the database and returned to the client.

    @RequestMapping(path = "/checkpoint", method = RequestMethod.POST)
    public ResponseEntity<Character> setCheckpoint(HttpSession session, @RequestBody Map<String, Integer> json) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return new ResponseEntity<Character>(HttpStatus.FORBIDDEN);
        }
        Character characterFromDb = characters.findOne(json.get("id"));
        characterFromDb.setScore(json.get("score"));
        characters.save(characterFromDb);
        return new ResponseEntity<Character>(characterFromDb, HttpStatus.OK);

    // route saving checkpoints, still determining the functionality of this route.

//    @RequestMapping(path = "/checkpoint", method = RequestMethod.POST)
//    public ResponseEntity<Character> setCheckpoint(HttpSession session, @RequestBody Character character) {
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            return new ResponseEntity<Character>(HttpStatus.FORBIDDEN);
//        }
//        Character characterFromDb = characters.findOne(character.getId());
//        characterFromDb.setMoney(character.getMoney());
//        characterFromDb.setScore(character.getScore());
//        characters.save(characterFromDb);
//        return new ResponseEntity<Character>(character, HttpStatus.OK);

        /*
            money: 590439853
            score: 2309844903

         */
    }

    // route to save the users inventory.

    @RequestMapping(path = "/inventory", method = RequestMethod.POST)
    public Iterable<Item> saveInventory(HttpSession session, ArrayList<Item> items) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in");
        }
        User user = users.findFirstByUsername(username);
        for (int i = 0; i < items.size(); i++) {
            String file = items.get(i).getFilename();
            useritems.save(new Item(file, user));
        }
        return useritems.findByUser(user);
    }

    @RequestMapping(path = "/inventory", method = RequestMethod.GET)
    public Iterable<Item> getInventory(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in");
        }
        User user = users.findFirstByUsername(username);
        //User user = users.findFirstByUsername("mike");
        return useritems.findByUser(user);
    }

    // route to change the level.

    @RequestMapping(path = "/level", method = RequestMethod.POST)
    public ResponseEntity<Character> setLevel(HttpSession session, @RequestBody User user) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return new ResponseEntity<Character>(HttpStatus.FORBIDDEN);
        }
        Character character = characters.findByUser(user);
        character.setLevel(character.getLevel() + 1);
        characters.save(character);
        return new ResponseEntity<Character>(character, HttpStatus.OK);
    }

    // route returning a list of avatar assets with standing sprites for a user to select.

    @RequestMapping(path = "/avatars", method = RequestMethod.GET)
    public Iterable<Avatar> getAvatars(HttpSession session) throws Exception {
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            throw new Exception("Not logged in!");
//        }
        return avatars.findByAnimation(Avatar.Animation.STANDING);
    }

    // route returning an array list of NPC assets

    @RequestMapping(path = "/random-assets", method = RequestMethod.GET)
    public ArrayList<NPC> getRandomAssets(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in!");
        }
        User user = users.findFirstByUsername(username);
        //User user = users.findFirstByUsername("mike");
        Character character = characters.findByUser(user);
        ArrayList<NPC> theNpcs = new ArrayList<>();
        for (int i = 0; i < 60 + character.getCheckpoint(); i++) {
            double randNum =  Math.random();
            if (randNum <= .97) {
                int randId = (int) (Math.random() * (16 - 1)) + 1;
                // int randId = (int) Math.ceil(Math.random() * 15);
                theNpcs.add(npcs.findOne(randId));
            }
            else if (randNum > .97 && randNum <= .98) {
                int randId = (int) (Math.random() * (29 - 17)) + 17;
                //int randId = (int) Math.ceil(Math.random() * 11);
                theNpcs.add(npcs.findOne(randId));
            }
            else if (randNum > .98 && randNum <= .99) {
                theNpcs.add(npcs.findOne(16));
            }
            else {
                theNpcs.add(npcs.findOne(29));
            }
        }
        return theNpcs;
    }

    // route returning a random NPC asset, "NPC asset defined as an enemy, money, items and health" to the client.

    @RequestMapping(path = "/random-asset", method = RequestMethod.GET)
    public NPC getRandomAsset(HttpSession session) {

        double randNum =  Math.random();
        if (randNum <= .50) {
            int randId = (int) (Math.random() * (16 - 1)) + 1;
            // int randId = (int) Math.ceil(Math.random() * 15);
            return npcs.findOne(randId);
        }
        else if (randNum > .50 && randNum <= .75) {
            int randId = (int) (Math.random() * (29 - 17)) + 17;
            //int randId = (int) Math.ceil(Math.random() * 11);
            return npcs.findOne(randId);
        }
        else {
            return npcs.findOne(16);
        }
    }

    // route saving and returning the avatar the user selected.

    @RequestMapping(path = "/user-avatar", method = RequestMethod.POST)
    public Iterable<Avatar> postUserAvatar(HttpSession session, @RequestBody Avatar avatar) throws Exception {
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            throw new Exception("Not logged in!");
//        }
        User user = users.findFirstByUsername("mike");
        //User user = users.findFirstByUsername(username);
        //Avatar avatarFromDb = avatars.findOne(avatar.getId() + 1);
        Avatar avatar1 = avatars.findByFilename(avatar.getFilename());
        Avatar avatar2 = avatars.findOne(avatar1.getId() + 1);
        Avatar avatar3 = avatars.findOne(avatar2.getId() + 2);
        characters.save(new Character(avatar.getFilename(), avatar2.getFilename(), avatar3.getFilename(), 0, 0, 0, 0, user));
        //characters.save(new Character(avatarFromDb.getFilename(), 0, 0, user));
        return avatars.findByRace(avatar.getRace());
    }

    // route returning the avatar the user selected.

    @RequestMapping(path = "/user-avatar", method = RequestMethod.GET)
    public ArrayList<Avatar> getUserAvatar(HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in");
        }
        User user = users.findFirstByUsername(username);
        ArrayList<Avatar> theAvatars = new ArrayList<>();
        //User user = users.findFirstByUsername(username);
        //User user = users.findFirstByUsername("mike");
        //User user = users.findFirstByUsername("sam");
        //User user = users.findFirstByUsername("tom");
        //User user = users.findFirstByUsername("rob");
        //User user = users.findFirstByUsername("nick");
        //Character character = characters.findByUser(user.getId();
        Character character = characters.findByUser(user);
        Avatar avatar = avatars.findByFilename(character.getFilename());
        Avatar avatar1 = avatars.findOne(avatar.getId() + 1);
        theAvatars.add(avatar);
        theAvatars.add(avatar1);
        return theAvatars;
        //return characters.findByUser(user.getId());
    }

    // route returning all the characters with their scores in the list that is sent back.

    @RequestMapping(path = "/highscore", method = RequestMethod.GET)
    public Iterable<Character> getHighscores(HttpSession session) {
        return characters.findAll();
    }

    // route uploading a user submitted standing sprite.

    @RequestMapping(path = "/user-sprite-standing", method = RequestMethod.POST)
    public ResponseEntity<UserSprite> submitStandingSprite(HttpSession session, MultipartFile sprite) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in");
        }
        User user = users.findFirstByUsername(username);

        File dir = new File("usersprites");
        File spritefile = File.createTempFile("sprite", sprite.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(spritefile);
        fos.write(sprite.getBytes());

        UserSprite userSprite = new UserSprite(spritefile.getName(), UserSprite.Animation.STANDING, user);
        usersprites.save(userSprite);
        return new ResponseEntity<UserSprite> (userSprite, HttpStatus.OK);
    }

    // route uploading a user submitted jumping sprite.

    @RequestMapping(path = "/user-sprite-jumping", method = RequestMethod.POST)
    public ResponseEntity<UserSprite> submitJumpingSprite(HttpSession session, MultipartFile sprite) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in");
        }
        User user = users.findFirstByUsername(username);

        File dir = new File("usersprites");
        File spritefile = File.createTempFile("sprite", sprite.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(spritefile);
        fos.write(sprite.getBytes());

        UserSprite userSprite = new UserSprite(spritefile.getName(), UserSprite.Animation.JUMPING, user);
        usersprites.save(userSprite);
        return new ResponseEntity<UserSprite> (userSprite, HttpStatus.OK);
    }

    // route updating user fields returns the user object.

    @RequestMapping(path = "/update-user", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(HttpSession session, @RequestBody User user) throws PasswordStorage.CannotPerformOperationException {
        String username = (String) session.getAttribute("username");
        User userFromDb = users.findFirstByUsername(username);
        userFromDb.setPassword(PasswordStorage.createHash(user.getPassword()));
        userFromDb.setUsername(user.getUsername());
        users.save(userFromDb);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // route to delete a user from the database and redirects to the homepage.

    @RequestMapping(path = "/delete-user", method = RequestMethod.POST)
    public void deleteUser(HttpSession session, HttpServletResponse response) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        users.delete(user.getId());
        response.sendRedirect("/#/");
    }

    // route logging a user out.

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        session.invalidate();
    }

    // route to get back a boss

    @RequestMapping(path = "/boss", method = RequestMethod.GET)
    public Boss getBoss(HttpSession session) {
        return bosses.findOne(1);
    }

    // route to get back all the assets, all the projectiles the boss will be throwing, to the client.

    @RequestMapping(path = "bossassets", method = RequestMethod.GET)
    public ArrayList<BossAsset> getBossAssets(HttpSession session) {
        ArrayList<BossAsset> bossAssets = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            int randId = (int) (Math.random() * (5 - 1)) + 1;
            bossAssets.add(bossassets.findOne(randId));
        }
        return bossAssets;
    }
}
