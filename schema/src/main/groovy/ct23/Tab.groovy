package ct23

import acmi.l2.clientmod.util.Description
import acmi.l2.clientmod.util.IOEntity
import acmi.l2.clientmod.util.IOUtil
import acmi.l2.clientmod.util.Type

class Tab extends DefaultProperty {
    @Type(TabElement.class)
    List<TabElement> tabs = []

    static class TabElement implements IOEntity {
        @Description("sysstring")
        int buttonName = -9999
        String buttonNameText = "undefined"
        String target = "undefined"
        int width
        int height
        String normalTex = "undefined"
        String pushedTex = "undefined"
        boolean movable
        int gap
        @Description("sysstring")
        int tooltip = -1
        int noHighlight = -1

        @Override
        TabElement read(InputStream input) {
            use(IOUtil) {
                buttonName = input.readInt()
                buttonNameText = input.readString()
                target = input.readString()
                width = input.readInt()
                height = input.readInt()
                normalTex = input.readString()
                pushedTex = input.readString()
                movable = input.readBoolean()
                gap = input.readInt()
                tooltip = input.readInt()
                noHighlight = input.readInt()
            }
            this
        }

        @Override
        TabElement write(OutputStream output) {
            use(IOUtil) {
                output.writeInt(buttonName)
                output.writeString(buttonNameText)
                output.writeString(target)
                output.writeInt(width)
                output.writeInt(height)
                output.writeString(normalTex)
                output.writeString(pushedTex)
                output.writeBoolean(movable)
                output.writeInt(gap)
                output.writeInt(tooltip)
                output.writeInt(noHighlight)
            }
            this
        }

        @Override
        String toString() {
            getClass().simpleName
        }

        @Deprecated int getTitleStringId() { buttonName }
        @Deprecated void setTitleStringId(int titleStringId) { this.buttonName = titleStringId }

        @Deprecated String getTitleStringValue() { buttonNameText }
        @Deprecated void setTitleStringValue(String titleStringValue) { this.buttonNameText = titleStringValue }

        @Deprecated String getContextCtrl() { target }
        @Deprecated void setContextCtrl(String contextCtrl) { this.target = contextCtrl }

        @Deprecated int getHeadWidth() { width }
        @Deprecated void setHeadWidth(int headWidth) { this.width = headWidth }

        @Deprecated int getHeadHeight() { height }
        @Deprecated void setHeadHeight(int headHeight) { this.height = headHeight }

        @Deprecated String getTextureUnselected() { normalTex }
        @Deprecated void setTextureUnselected(String textureUnselected) { this.normalTex = textureUnselected }

        @Deprecated String getTextureSelected() { pushedTex }
        @Deprecated void setTextureSelected(String textureSelected) { this.pushedTex = textureSelected }

        @Deprecated boolean getFloatable() { movable }
        @Deprecated void setFloatable(boolean floatable) { this.movable = floatable }

        @Deprecated int getSpaceAfter() { gap }
        @Deprecated void setSpaceAfter(int spaceAfter) { this.gap = spaceAfter }

        @Deprecated int getUnk110() { tooltip }
        @Deprecated void setUnk110(int unk110) { this.tooltip = unk110 }

        @Deprecated int getUnk111() { noHighlight }
        @Deprecated void setUnk111(int unk111) { this.noHighlight = unk111 }
    }

    @Override
    Tab read(InputStream input) {
        super.read(input)

        use(IOUtil) {
            int count = input.readInt()
            for (int i = 0; i < count; i++)
                tabs.add(new TabElement().read(input))
        }

        this
    }

    @Override
    Tab write(OutputStream output) {
        super.write(output)

        use(IOUtil) {
            output.writeInt(tabs.size())
            for (TabElement tabElement : tabs)
                tabElement.write(output)
        }

        this
    }
}
