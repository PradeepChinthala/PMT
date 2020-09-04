package project.features.steps.definations;

import org.junit.Assert;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.steps.ScenarioSteps;

public class TransferRequest_StepDef extends ScenarioSteps{

	
	public static void Verify(String StepDetails, String sStatus) {
        if (sStatus.equalsIgnoreCase("PASSED")) {
            System.out.println(StepDetails);
            Serenity.recordReportData().withTitle(StepDetails).andContents(sStatus);
            Assert.assertTrue(StepDetails, true);
        } else {
            Serenity.recordReportData().withTitle(StepDetails).andContents(sStatus);
            Serenity.takeScreenshot();
            System.out.println(StepDetails);
            Assert.assertTrue(StepDetails, false);
        }
    }
}
