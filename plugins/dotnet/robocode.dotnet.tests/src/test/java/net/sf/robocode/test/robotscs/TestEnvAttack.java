/*******************************************************************************
 * Copyright (c) 2001-2014 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 *******************************************************************************/
package net.sf.robocode.test.robotscs;


import net.sf.robocode.test.helpers.Assert;
import net.sf.robocode.test.helpers.RobocodeTestBed;
import org.junit.Test;
import robocode.control.events.TurnEndedEvent;


/**
 * @author Pavel Savara (original)
 */
public class TestEnvAttack extends RobocodeTestBed {
	boolean messagedAttack;

	@Test
	public void run() {
		super.run();
	}

	@Override
	public String getRobotNames() {
		return "tested.robotscs.BattleLost,tested.robotscs.EnvAttack";
	}

	@Override
	protected int getExpectedErrors() {
		return 1;
	}

	@Override
	public void onTurnEnded(TurnEndedEvent event) {
		super.onTurnEnded(event);
		final String out = event.getTurnSnapshot().getRobots()[1].getOutputStreamSnapshot();

		if (out.contains("System.Security.Permissions.EnvironmentPermission")) {
			messagedAttack = true;
		}
	}

	@Override
	protected void runTeardown() {
		Assert.assertTrue(messagedAttack);
	}
}
