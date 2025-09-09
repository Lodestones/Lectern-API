package gg.lode.lecternapi.packetmenu;

public interface ButtonListener {
    void onClick(Origin origin);

    default PendingUnHover onHover(Origin origin) {
        return PendingUnHover.NONE;
    }

    interface Origin {
        String rawButtonId();

        PacketMenu menu();

        String extractNewId();

        record Direct(String rawButtonId, PacketMenu menu) implements Origin {
            @Override
            public String extractNewId() {
                return rawButtonId() + "direct";
            }
        }

        class AnyFork implements Origin {
            private final Direct direct;
            private final int index;

            public AnyFork(Direct direct, int index) {
                this.direct = direct;
                this.index = index;
            }

            @Override
            public String rawButtonId() {
                return direct.rawButtonId;
            }

            @Override
            public PacketMenu menu() {
                return direct.menu;
            }

            @Override
            public String extractNewId() {
                return rawButtonId() + "any_fork" + index;
            }
        }
    }

    interface PendingUnHover {
        PendingUnHover NONE = ()-> {};

        void onUnHover();
    }
}
