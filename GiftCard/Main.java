package GiftCard;

import GiftCard.CardSystem.cardView;
import GiftCard.CardSystem.cardViewModel;

public class Main {
    public static void main(String[] args) {

        cardView view = new cardView();
        cardViewModel model = new cardViewModel(view);

         view.run();

    }
}
