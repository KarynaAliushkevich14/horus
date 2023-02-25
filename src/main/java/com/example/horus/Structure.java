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

    //all overrides
    @Override
    public int count() {
        int sizeOfBlocks = blocks.size();
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) { // jeśli blok jest obiektem CompositeBlock i ma w sobie inne bloki
                sizeOfBlocks = sizeOfBlocks + ((CompositeBlock) block).getBlocks().size();
                return sizeOfBlocks;
            }
        }
        return sizeOfBlocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return getBlocksByParameter(color).stream().findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return getBlocksByParameter(material);
    }

    private List<Block> getBlocksByParameter(String param) { // unikanie powielania kodu
        List<Block> blocksWithParameters = new ArrayList<>();
        List<Block> compositeBlocksWithParameters = new ArrayList<>();

        for (Block block : blocks) {
            // sprawdzamy czy blok jest obiektem CompositeBlock
            if (block instanceof CompositeBlock) {
                List<Block> listOfCompoositeBlocks = ((CompositeBlock) block).getBlocks();
                for (Block blockFromCompositeBlock : listOfCompoositeBlocks) {
                    if (blockFromCompositeBlock.getColor().equals(param) || blockFromCompositeBlock.getMaterial().equals(param)) {
                        compositeBlocksWithParameters.add(blockFromCompositeBlock);
                        return compositeBlocksWithParameters;
                    }
                }
            }
            // sprawdzamy czy blok jest obiektem Blok
            if (block.getColor().equals(param) || (block.getMaterial().equals(param))) {
                blocksWithParameters.add(block);
                return blocksWithParameters;
            }
        }
        return null;
    }
}

interface Block {
    String getColor();
    String getMaterial();
}

interface CompositeBlock extends Block {
    List<Block> getBlocks();
}
