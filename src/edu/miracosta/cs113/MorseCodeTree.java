package edu.miracosta.cs113;

import java.io.*;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    public static void main(String[] args)
    {
        MorseCodeTree mct = new MorseCodeTree();
        try
        {
            System.out.println(mct.translateFromMorseCode("** *- -* --"));
        }
        catch (Exception e)
        {
            System.out.println("REEEEEE");
        }

    }

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */

    public String translateFromMorseCode(String morseCode) throws Exception{
        String message = "";
        BinaryTree<Character> current = buildMorseCodeTree();

        String[] splitted = morseCode.split(" ");

        for (int j = 0; j < splitted.length; j++)
        {
            for (int i = 0; i < splitted[j].length(); i++) {
                if (splitted[j].charAt(i) == '*' && current.getLeftSubtree() != null) {
                    current = current.getLeftSubtree();
                } else if (splitted[j].charAt(i) == '-' && current.getRightSubtree() != null) {
                    current = current.getRightSubtree();
                }
                else
                {
                    throw new Exception();
                }
            }
            message += current.getData();
            current = buildMorseCodeTree();
        }
        return message;
    }

//    public String readMorseCodeTree(BinaryTree<Character> character, String morseCode, String message)
//    {
//        for (int i = 0; i < morseCode.length(); i++)
//        {
//            if (morseCode.charAt(i) == '*')
//            {
//                if ()
//                character = getLeftSubtree();
//                readMorseCodeTree(character, morseCode.substring(1), message);
//            }
//            else if (morseCode.charAt(i) == '-')
//            {
//                character = getRightSubtree();
//                readMorseCodeTree(character, morseCode.substring(1), message);
//            }
//            else
//            {
//                message += character.root.data;
//            }
//        }
//        return message;
//    }

    public BinaryTree buildMorseCodeTree()
    {
        BinaryTree<Character> morseCodeTree = new BinaryTree<>(null, null, null);
        try {
            File myObj = new File("src/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                BinaryTree<Character> current = morseCodeTree;
                String line = myReader.nextLine();
                String[] splitted = line.split(" ");
                Character letter = splitted[0].charAt(0);
                String code = splitted[1];
                for (int i = 0; i < code.length(); i++)
                {
                    if (code.charAt(i) == '*')
                    {
                        if (current.getLeftSubtree() == null) {
                            current.setLeftSubtree(letter);
                        }
                        current = current.getLeftSubtree();
                    }
                    else if (code.charAt(i) == '-')
                    {
                        if (current.getRightSubtree() == null) {
                            current.setRightSubtree(letter);
                        }
                        current = current.getRightSubtree();
                    }
                }
//                current.root.data = letter;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return morseCodeTree;
    }

} // End of class MorseCodeTree