import io.cucumber.datatable.DataTable;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStepdefs
{
    private int faktor;
    private List<Integer> verlauf;
    private List<Integer> result;


    private Exception expectedException;

    @Then("an exception should be thrown")
    public void anExceptionShouldBeThrown()
    {
        assertThat(expectedException).isNotNull().hasMessageContaining("unsupported");
    }


    @Given("a sequence")
    public void aSequence(@Transpose DataTable table)
    {
        //Working with DataTable
        //https://stackoverflow.com/questions/46130455/how-to-convert-a-datatable-in-cucumber-to-a-list-of-objects
        //https://www.baeldung.com/cucumber-data-tables
        //http://www.automationtestinghub.com/cucumber-data-table/
        verlauf = table.<String>asList(String.class).stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    @Given("factor = {int}")
    public void factor(int faktor)
    {
        this.faktor = faktor;
    }

    @io.cucumber.java.en.When("^start the calculation$")
    public void startTheCalculation()
    {
        try
        {
            //FIXME delegate to business logic
            if (faktor == 0)
            {
                throw new UnsupportedOperationException(faktor + " is unsupported");
            }
            this.result = verlauf.stream().map(x -> x * faktor).collect(Collectors.toList());
        } catch (Exception e)
        {
            //Emulation of @ExpectedException-Rule in JUnit5
            expectedException = e;
        }
    }

    @Then("the result must be")
    public void theResultMustBe(@Transpose DataTable expected)
    {
        List<Integer> expectedSequence = expected.<String>asList(String.class).stream().map(Integer::valueOf).collect(Collectors.toList());

        assertThat(result).containsExactlyElementsOf(expectedSequence);
    }
}
