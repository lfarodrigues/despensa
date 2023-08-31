package com.example.despensa.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;

import com.example.despensa.objects.RecycleTip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TipsManager {

    private static final String TIPS_PREFERENCES = "TipsPreferences";
    private static final String KEY_TIPS = "tips";

    private List<RecycleTip> newTips;
    private int newTipsCount;
    private List<RecycleTip> myTips;
    private Random random;

    public TipsManager() {
        newTips = new ArrayList<>();
        myTips = new ArrayList<>();
        random = new Random();
        newTipsCount = 0;

        createDummyTips();
    }

    private void createDummyTips(){
        myTips.add(new RecycleTip("Separe os resíduos", "Crie um sistema de coleta seletiva em casa, separando seus resíduos em categorias como papel, plástico, vidro e metal. Isso facilita o processo de reciclagem e evita a contaminação de materiais recicláveis."));
        myTips.add(new RecycleTip("Reduza o consumo de plástico", "O plástico é um dos maiores desafios ambientais. Evite produtos de plástico descartáveis, como sacolas, garrafas e talheres. Opte por itens reutilizáveis, como garrafas de água de metal e sacolas de pano."));
        myTips.add(new RecycleTip("Compre de forma consciente", "Escolha produtos feitos com materiais reciclados ou que sejam duráveis e de qualidade. Ao investir em produtos que durem mais, você reduzirá a necessidade de substituí-los com frequência, diminuindo o desperdício."));
        myTips.add(new RecycleTip("Economize energia", "Reduza o consumo de energia em casa trocando lâmpadas incandescentes por LED, desligando aparelhos eletrônicos quando não estiverem em uso e utilizando eletrodomésticos eficientes em termos energéticos. Isso diminui tanto suas contas de energia quanto a demanda por recursos naturais."));
        myTips.add(new RecycleTip("Pratique a compostagem", "Transforme resíduos orgânicos, como restos de comida e folhas, em adubo rico em nutrientes através da compostagem. Isso não apenas reduz o volume de resíduos que vão para aterros, mas também enriquece o solo do seu jardim."));
    }
    public void selectNewTip() {
        if (myTips.isEmpty()) {
            return;
        }

        int index = random.nextInt(myTips.size());
        RecycleTip selectedTip = myTips.remove(index);
        newTips.add(selectedTip);
        newTipsCount++;
    }

    public List<RecycleTip> getNewTips() {
        return newTips;
    }

    public void addTip(RecycleTip tip) {
        myTips.add(tip);
    }

    public void removeTip(RecycleTip tip) {
        myTips.remove(tip);
    }

    public void initializeTips(List<RecycleTip> initialTips) {
        myTips.addAll(initialTips);
    }

    public int getNewTipsCount(){
        return newTipsCount;
    }
    public void setNewTipsCount(int value){
        this.newTipsCount = value;
    }
}
