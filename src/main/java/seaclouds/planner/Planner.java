package seaclouds.planner;

import seaclouds.utils.toscamodel.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class Planner {
    final private Optimizer optimizer = new Optimizer();
    final private Discoverer discoverer = Discoverer.instance();
    final private Matchmaker matchmaker = new Matchmaker(discoverer.getOfferings());

    public Planner() {
    }

    List<IToscaEnvironment> plan(IToscaEnvironment aam) {
        Map<String, List<INodeType>> matches = matchmaker.Match(aam);
        return optimizer.optimize(aam, matches);
    }
}
