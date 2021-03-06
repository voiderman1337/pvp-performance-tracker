/*
 * Copyright (c) 2020, Matsyir <https://github.com/Matsyir>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.pvpperformancetracker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("pvpperformancetracker")
public interface PvpPerformanceTrackerConfig extends Config
{
	@ConfigItem(
		keyName = "restrictToLms",
		name = "Restrict to LMS",
		description = "Restricts use within the LMS area. WARNING: can be inaccurate outside LMS, as every attack animation's combat style must be manually mapped.",
		position = 0
	)
	default boolean restrictToLms()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showFightOverlay",
		name = "Show Fight Overlay",
		description = "Display an overlay of statistics while fighting.",
		position = 1
	)
	default boolean showFightOverlay()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showOverlayTitle",
		name = "Show Overlay Title",
		description = "The overlay will have a title to say it's the PvP Performance Tracker.",
		position = 2
	)
	default boolean showOverlayTitle()
	{
		return false;
	}

	@ConfigItem(
		keyName = "useSimpleOverlay",
		name = "Use Simple Overlay",
		description = "The overlay will only display percentage as stats rather than fraction & percentage.",
		position = 3
	)
	default boolean useSimpleOverlay()
	{
		return true;
	}

	@ConfigItem(
		keyName = "saveFightHistory",
		name = "Save Fight History",
		description = "Enables the side-panel which displays previous fight statistics.",
		position = 4
	)
	default boolean saveFightHistory()
	{
		return true;
	}
}
