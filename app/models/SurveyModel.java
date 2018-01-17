package models;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by daniel.rothig on 21/10/2016.
 *
 * Class representing a survey. Use SurveyModel::make() to get an instance with all questions
 */
public class SurveyModel {
    public Map<Question, Integer> questionsAndAnswers;
    private final List<DigitalRole> roles;

    public SurveyModel(Map<Question, Integer> questionsAndAnswers, List<DigitalRole> roles) {
        this.questionsAndAnswers= questionsAndAnswers;
        this.roles = roles;
    }

    public Map<DigitalRole, BigDecimal> getScores() {
        HashMap<DigitalRole, BigDecimal> rtn = new HashMap<>();

        for (DigitalRole role: roles) {
            double score = 0;
            double bestScore = 0;
            double worstScore = 0;
            for (Map.Entry<Question, Integer> pair: questionsAndAnswers.entrySet()) {
                Double weight = pair.getKey().roleMap.get(role);
                score += pair.getValue() * weight;
                bestScore += Math.max(weight, -weight);
                worstScore += Math.min(weight, -weight);
            }

            double relativeScore = bestScore == worstScore
                    ? 0
                    : 100.0 * (score - worstScore) / (bestScore - worstScore);

            BigDecimal bigDecimal = new BigDecimal(relativeScore).setScale(1, BigDecimal.ROUND_HALF_UP);

            rtn.put(role, bigDecimal);
        }

        return rtn;
    }

    public static SurveyModel make() {
        List<Question> questions = Arrays.asList(

                //   BA CD DM  D UR
                q( 1, 1, 0, 1, 0, 0, "Do your friends and colleagues describe you as \"the most organised person they know\"?"),
                q( 2, 0, 1, 0, 0, 0, "Can you talk clearly about why something does or doesn't work?"),
                q( 3, 0, 1, 0, 0, 0, "Do you enjoy writing?"),
                q( 4, 1, 0, 0, 0, 1, "Are you curious about what people need to make the things they do better?"),
                q( 5, 0, 0, 1, 0, 0, "Are you the person encouraging your work colleagues, family or sports team to keep going to meet a challenge?"),
                q( 6, 1, 0, 0, 0, 0, "Do you enjoy working with data to analyse it and look for patterns, drawing conclusions from it?"),
                q( 7, 0, 0, 0, 0, 0, "Do you prefer to make sense of complicated things by laying out all the information so you can see it all together in one place, then moving the bits around until the whole thing makes more sense? Do you sometimes help others do this?"),
                q( 8, 0, 0, 1, 0, 0, "Do you have a record of helping get the best out of others?"),
                q( 9, 1, 1, 1, 1, 1, "Are you willing to try new things out, work in new ways as part of a team of people who do different things?"),
                q(10, 1, 0, 1, 0, 0, "Have you ever approached your manager with a proposal for changing how something gets done?"),
                q(11, 0, 1, 0, 0, 0, "Could writing be a bigger part of your job in future?"),
                q(12, 0, 0, 1, 0, 0, "Have you ever done any side projects at work or at home that involved finding volunteers to help?"),
                q(13, 0, 1, 0, 0, 0, "Do people ask for your help with writing or editing, either at work or outside (for example, a community newsletter or a club website)?"),
                q(14, 1, 0, 0, 0, 1, "Do you enjoy looking for evidence over assumptions/guesses?"),
                q(15, 0, 1, 0, 1, 0, "Do you find yourself making or improving other people's presentation slides, posters or leaflets, because you know how to make them look better?"),
                q(16, 0, 0, 0, 0, 1, "Do you enjoy meeting people and finding out what motivates them?"),
                q(17, 0, 1, 0, 0, 0, "Do you want to sharpen your writing skills?"),
                q(18, 0, 0, 0, 0, 1, "Do you enjoy helping people understand how something works?"),
                q(19, 0, 0, 1, 0, 0, "Are you the one who organises meetups with your team, friends or family?"),
                q(20, 1, 0, 1, 0, 1, "Do you enjoy getting things done?"),
                q(21, 0, 0, 1, 0, 0, "If there’s a disagreement in the workplace or at home, do you act as the peace broker?"),
                q(22, 0, 0, 1, 0, 0, "Have you ever shielded your team from a difficult stakeholder?"),
                q(23, 1, 0, 0, 0, 1, "Do you agree that numbers only tell you half the truth?"),
                q(24, 0, 0, 1, 0, 1, "Are you patient with people and understand it takes time to learn new things?"),
                q(25, 0, 0, 1, 0, 0, "Do you help build consensus and encourage people to move forward together?"),
                q(26, 0, 0, 0, 1, 0, "Do you like making things, or have a creative hobby like painting, knitting, woodwork, or photography?"),
                q(27, 0, 1, 0, 0, 0, "Can you write clearly and in plain English?"),
                q(28, 1, 1, 1, 0, 1, "Have you ever been praised by a colleague or by your boss for demonstrating great teamwork?"),
                q(29, 1, 0, 0, 0, 0, "Do you enjoy mapping out work flows to understand the journey of a process from start to finish?"),
                q(30, 0, 1, 0, 1, 1, "Do you see things on websites and mobile apps that frustrate you and that you wish you could fix?"),
                q(31, 1, 1, 0, 0, 1, "Do you have an enquiring mind and do you keep asking \"why, why, why?\" so that you make things better?"),
                q(32, 1, 0, 0, 0, 0, "Do you find yourself acting as an interpreter between two sets of colleagues, helping one understand the needs of the other?"),
                q(33, 0, 0, 1, 0, 0, "Do people describe you as a \"people person\"?"),
                q(34, 1, 0, 1, 0, 0, "Do you reflect on how your team does its work, and suggest improvements?"),
                q(35, 0, 1, 0, 0, 0, "Do you always think about your audience and what they need to get out of your message?"),
                q(36, 0, 0, 0, 0, 0, "When you're making something, do you willingly share your work in progress with others, to get feedback?"),
                q(37, 1, 1, 1, 0, 1, "Do you enjoy sorting chaos into organised clarity?"),
                q(38, 0, 0, 0, 1, 0, "Do you notice good design, and bad design? Not just in terms of how a thing looks, but whether it functions the way people need it to (which may not always be what you personally want)?"),
                q(39, 0, 0, 1, 0, 0, "In work meetings, are you the person helping the team focus on the task at hand?"),
                q(40, 1, 0, 1, 1, 0, "Are you organised and detail-oriented?"),
                q(41, 1, 1, 1, 1, 1, "Do you enjoy working with others?"),
                q(42, 1, 0, 0, 0, 1, "Are you curious about how and why people behave the way they do when they undertake certain tasks?"),
                q(43, 0, 1, 0, 1, 0, "Do you take pride in what you create, whether it’s a work document, a personal blog or a poster?"),
                q(44, 1, 1, 1, 0, 1, "Do people tell you how great it is to work with you?"),
                q(45, 0, 1, 0, 0, 0, "Do you have excellent spelling, grammar and punctuation?"),
                q(46, 1, 0, 1, 0, 0, "Do you keep \"to do\" lists or other notes to manage your work, and do you always check them off when they’re done?")



        );


        Map<Question, Integer> map = new HashMap<>();
        for (Question q : questions) {
            map.put(q, 0);
        }

        return new SurveyModel(map, new ArrayList(questions.get(0).roleMap.keySet()));
    }

    private static Question q(int questionId, double businessAnalystScore, double contentDesignerScore, double deliveryManagerScore, double designerScore, double userResearcherScore, String questionText) {
        HashMap<DigitalRole, Double> roleFits = new HashMap<>();
        roleFits.put(DigitalRole.BUSINESS_ANALYST, businessAnalystScore);
        roleFits.put(DigitalRole.CONTENT_DESIGNER, contentDesignerScore);
        roleFits.put(DigitalRole.DELIVERY_MANAGER, deliveryManagerScore);
        roleFits.put(DigitalRole.DESIGNER, designerScore);
        roleFits.put(DigitalRole.USER_RESEARCHER, userResearcherScore);

        return new Question(questionId, questionText, roleFits);

    }

    public static SurveyModel makeWithAnswers(Map<String, String[]> params) {
        SurveyModel rtn = make();
        Set<Question> questions = rtn.questionsAndAnswers.keySet();
        for (Question q: rtn.questionsAndAnswers.keySet()){
            if (params.containsKey("question_" + q.id)) {
                rtn.questionsAndAnswers.put(q, Integer.parseInt(params.get("question_"+q.id)[0]));
            }
        }
        return rtn;
    }
}
