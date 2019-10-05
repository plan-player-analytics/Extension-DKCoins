/*
 * Copyright (c) 2019 Plan | Player Analytics
 *
 * The MIT License(MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.djrapitops.extension;

import ch.dkrieger.coinsystem.core.CoinSystem;
import ch.dkrieger.coinsystem.core.player.CoinPlayer;
import com.djrapitops.plan.extension.CallEvents;
import com.djrapitops.plan.extension.DataExtension;
import com.djrapitops.plan.extension.NotReadyException;
import com.djrapitops.plan.extension.annotation.NumberProvider;
import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.annotation.StringProvider;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;

import java.util.UUID;

/**
 * DataExtension for the DKCoins
 *
 * @author Vankka
 */
@PluginInfo(name = "DKCoins", iconName = "coins", iconFamily = Family.SOLID, color = Color.YELLOW)
public class DKCoinsExtension implements DataExtension {

    @Override
    public CallEvents[] callExtensionMethodsOn() {
        return new CallEvents[]{CallEvents.PLAYER_JOIN};
    }

    private CoinSystem getCoinSystem() {
        return CoinSystem.getInstance();
    }

    private CoinPlayer getCoinPlayer(UUID playerUUID) {
        CoinPlayer player = getCoinSystem().getPlayerManager().getPlayer(playerUUID);
        if (player == null) throw new NotReadyException();
        return player;
    }

    @NumberProvider(
            text = "Coins",
            description = "The amount of coins the player has on DKCoins",
            priority = 100,
            iconName = "coins",
            iconColor = Color.GREEN
    )
    public long coins(UUID playerUUID) {
        return getCoinPlayer(playerUUID).getCoins();
    }

    @StringProvider(
            text = "Name",
            description = "The name for the player on DKCoins",
            priority = 99,
            iconName = "signature",
            iconColor = Color.GREEN
    )
    public String name(UUID playerUUID) {
        return getCoinPlayer(playerUUID).getName();
    }
}