package org.citytechmaps.ui.list.adapter;

/**
 * 2015
 * Created by habibokanla on 01/03/15.
 */
public enum MenuSection {

    UNLISTED(-1),
    BUILDINGS(0);

    public int menuListPosition;

    MenuSection(int position) {
        this.menuListPosition = position;
    }
}
