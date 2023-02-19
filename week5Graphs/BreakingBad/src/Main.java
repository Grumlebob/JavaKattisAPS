import java.util.*;

//Huge help from https://github.com/dakoval

/*

public class Main {
    static HashMap<String, HashSet<String>> itemsWithValueOfRestrictedCombinations = new HashMap<>();
    static HashSet<String> jesseItems = new HashSet<>();
    static HashSet<String> walterItems = new HashSet<>();
    static boolean solutionPossible = true;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int amountOfItems = sc.nextInt();
        for (int i = 0; i < amountOfItems; i++) {
            String item = sc.next();
            itemsWithValueOfRestrictedCombinations.put(item, new HashSet<>());
        }

        int disallowedItems = sc.nextInt();
        for (int i = 0; i < disallowedItems; i++) {
            String disallowedOne = sc.next();
            String disallowedTwo = sc.next();
            itemsWithValueOfRestrictedCombinations.get(disallowedOne).add(disallowedTwo);
            itemsWithValueOfRestrictedCombinations.get(disallowedTwo).add(disallowedOne);
        }

        for (String s : itemsWithValueOfRestrictedCombinations.keySet()) {
            if (!solutionPossible) {
                break;
            }
            attemptToSplitItems(s);
        }

        if (solutionPossible) {
            for (String item : walterItems) {
                System.out.print(item + " ");
            }
            System.out.println();
            for (String item : jesseItems) {
                System.out.print(item + " ");
            }
        } else {
            System.out.println("impossible");
        }

    }

    static void attemptToSplitItems(String itemToBuy) {
        //Default to give Item to Walter first, if neither have it.
        if (!walterItems.contains(itemToBuy) && !jesseItems.contains(itemToBuy)) {
            walterItems.add(itemToBuy);
        }
        for (String restrictedItem : itemsWithValueOfRestrictedCombinations.get(itemToBuy)) {
            if (walterItems.contains(itemToBuy) && !walterItems.contains(restrictedItem) && !jesseItems.contains(restrictedItem)) {
                //If walter has the item, and jesse doesn't have the restricted item, give the restricted item to jesse.
                jesseItems.add(restrictedItem);
                //Recurse on the restricted item, to see if the list of the restricted item, can be given to Walter.
                attemptToSplitItems(restrictedItem);
            } else if (jesseItems.contains(itemToBuy) && !jesseItems.contains(restrictedItem) && !walterItems.contains(restrictedItem)) {
                //If jesse has the item, and walter doesn't have the restricted item, give the restricted item to walter.
                walterItems.add(restrictedItem);
                //Recurse on the restricted item, to see if the list of the restricted item, can be given to Jesse.
                attemptToSplitItems(restrictedItem);
            } else if ((jesseItems.contains(itemToBuy) && jesseItems.contains(restrictedItem)) || (walterItems.contains(itemToBuy) && walterItems.contains(restrictedItem))) {
                //If neither can handle the item with its restrictions, then it is impossible.
                solutionPossible = false;
                return;
            }
        }
    }
}

 */
