# Scene2d - Skin programmatically

Adding to `Skin()` programmatically.

Using `gdx-tools` extension, load `uiskin.json` and assets:

```
    skin = new Skin(Gdx.files.internal("uiskin.json"));
```

Getting existing assets:

```
    Label.LabelStyle labelStyle = skin.get("default", Label.LabelStyle.class);
    Drawable background = skin.get("default-pane", Drawable.class);
```

Create new style and to `skin`:

```
    TextTooltip.TextTooltipStyle textTooltipStyle = new TextTooltip.TextTooltipStyle();
    textTooltipStyle.label = labelStyle;
    textTooltipStyle.background = background;
    textTooltipStyle.wrapWidth = 150f;
    skin.add("default", textTooltipStyle);
```
