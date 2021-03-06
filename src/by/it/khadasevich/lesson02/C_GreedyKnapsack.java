import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            //тут может быть ваш компаратор
            if (this.cost / this.weight < o.cost / o.weight) {
                return 1;
            }
            if (this.cost / this.weight > o.cost / o.weight) {
                return -1;
            }

            return 0;

        }
    }
        double calc(File source) throws FileNotFoundException {
            Scanner input = new Scanner(source);
            int n = input.nextInt();      //сколько предметов в файле
            int W = input.nextInt();      //какой вес у рюкзака
            Item[] items = new Item[n];   //получим список предметов
            for (int i = 0; i < n; i++) { //создавая каждый конструктором
                items[i] = new Item(input.nextInt(), input.nextInt());
            }
            //покажем предметы
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

            //тут необходимо реализовать решение задачи
            //итогом является максимально воможная стоимость вещей в рюкзаке
            //вещи можно резать на кусочки (непрерывный рюкзак)
            double result = 0;
            //тут реализуйте алгоритм сбора рюкзака
            //будет особенно хорошо, если с собственной сортировкой
            //ваше решение. кроме того, можете описать свой компаратор в классе Item
            Arrays.sort(items);

            int tempWeight = 0;
            for (int i = 0; i < items.length; i++){
                if (tempWeight + items[i].weight < W ){
                    result = result + items[i].cost;
                    tempWeight = tempWeight + items[i].weight;
                }else
                if(tempWeight < W) {

                    result = result + (items[i].cost / items[i].weight) * (W - tempWeight);
                    tempWeight = tempWeight + (W - tempWeight);


                }
            }

            System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
            return result;
        }

        public static void main(String[] args) throws FileNotFoundException {
            long startTime = System.currentTimeMillis();
            String root = System.getProperty("user.dir") + "/src/";
            File f = new File(root + "/by/it/khadasevich/lesson02/greedyKnapsack.txt");
            double costFinal = new C_GreedyKnapsack().calc(f);
            long finishTime = System.currentTimeMillis();
            System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
        }
    }
