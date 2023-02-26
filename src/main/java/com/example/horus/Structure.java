package com.example.horus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

interface Structure {
    // zwraca dowolny element o podanym kolorze
    Optional<Block> findBlockByColor(String color);

    // zwraca wszystkie elementy z danego materiału
    List<Block> findBlocksByMaterial(String material);

    //zwraca liczbę wszystkich elementów tworzących strukturę
    int count();
}

class Wall implements Structure {
    private List<Block> blocks;

    public Wall (List<Block> blocks) {
        this.blocks = blocks;
    }

    //all overrides
    @Override
    public int count() {
        int sizeOfBlocks = blocks.size();
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) { // jeśli blok jest obiektem CompositeBlock i ma w sobie inne bloki
                sizeOfBlocks = sizeOfBlocks + ((CompositeBlock) block).getBlocks().size();
            }
        }
        return sizeOfBlocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            // sprawdzamy czy blok jest obiektem CompositeBlock
            if (block instanceof CompositeBlock) {
                List<Block> listOfCompoositeBlocks = ((CompositeBlock) block).getBlocks();
                for (Block blockFromCompositeBlock : listOfCompoositeBlocks) {
                    if (blockFromCompositeBlock.getColor().equals(color)) {
                        return Optional.of(blockFromCompositeBlock);
                    }
                }
            }
            // sprawdzamy czy blok jest obiektem Blok
            else if (block.getColor().equals(color)) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> blocksWithParameters = new ArrayList<>();

        for (Block block : blocks) {
            // sprawdzamy czy blok jest obiektem CompositeBlock
            if (block instanceof CompositeBlock) {
                List<Block> listOfCompoositeBlocks = ((CompositeBlock) block).getBlocks();
                for (Block blockFromCompositeBlock : listOfCompoositeBlocks) {
                    if (blockFromCompositeBlock.getMaterial().equals(material)) {
                        blocksWithParameters.add(blockFromCompositeBlock);
                    }
                }
            }
            // sprawdzamy czy blok jest obiektem Blok
            else if (block.getMaterial().equals(material)) {
                blocksWithParameters.add(block);
            }
        }
        return blocksWithParameters;
    }
}

interface Block {
    String getColor();
    String getMaterial();
}

interface CompositeBlock extends Block {
    List<Block> getBlocks();
}
