package org.xl.game;

import org.xl.framework.context.Application;
import org.xl.framework.context.support.TextGameFramework;

@TextGameFramework
public class TextGameApplication {
    public static void main(String[] args) {
        Application.run(TextGameApplication.class);
    }
}
