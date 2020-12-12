package com.github.biancacristina.compiler.syntax.grammar.impl;

import com.github.biancacristina.compiler.syntax.ParserInterface;
import com.github.biancacristina.compiler.syntax.grammar.ItemInterface;
import com.github.biancacristina.compiler.syntax.grammar.ItemType;
import com.github.biancacristina.compiler.syntax.grammar.exception.SyntaxException;

import java.util.ArrayList;

public class Production implements ItemInterface {
    private String label;
    private ArrayList<Sentence> orRules;
    private ParserInterface parser;

    public Production(String label) {
        this.label = label;
        this.orRules = new ArrayList<>();
    }

    @Override
    public ItemType getType() {
        return ItemType.NON_TERMINAL;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public void process() {
        // TODO: check first
        System.out.println("PROCESSING RULE < " + this.label + " > ------------------------");
        for (Sentence sentence: this.orRules) {
            String nextTokenLabel = this.parser.getCurrentToken().getLabel();
            if(!sentence.canProcess(nextTokenLabel)) {
                continue;
            }
            sentence.processAll();
            return;
        }
        this.error();
    }

    public void addSentence(Sentence sentence) {
        //System.out.println("add sentence: ");
        //sentence.getAll().forEach(item -> System.out.println(item.getLabel()));
        this.orRules.add(sentence);
    }

    public ArrayList<Sentence> getOrRules() {
        return orRules;
    }

    public void error() throws SyntaxException{
        throw new SyntaxException("Syntax Error after <" + this.label + "> rule.");
    }

    public void setParser(ParserInterface parser) {
        this.parser = parser;
        this.orRules.forEach(rule -> {
            rule.getAll().forEach(item -> {
                item.setParser(parser);
            });
        });
    }
}
