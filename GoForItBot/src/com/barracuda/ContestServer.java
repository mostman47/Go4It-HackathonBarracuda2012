/**
 * programming contest dufus
 */
package com.barracuda;

import org.apache.xmlrpc.webserver.WebServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContestServer {
    public static Logger log = Logger.getLogger(ContestServer.class.getName());
    public Boolean ping() {
        log.info("ping");
        return true;
    }

    public void init_game(Map<String, Object> state) {
        log.info("init_game");
        GameState gameState = new GameState(state);
        log.fine(gameState.toString());
    }

    public Integer get_bid(List<Integer> offer, Map<String, Object> state) {
        log.info("get_bid");
        log.info(offer.toString());
        GameState gameState = new GameState(state);
        log.fine(gameState.toString());
        Bot bot = new Bot4(gameState);
        if(gameState.opponent_id == 17) {
            bot = new Bot5(gameState);
        }
        //System.out.println(Arrays.toString(gameState.owned_squares.toArray()));
        //System.out.println(Arrays.toString(gameState.board.toArray()));
        //System.out.println(Arrays.toString(offer.toArray()));

        return bot.bid(offer);
    }

    public Integer make_choice(List<Integer> offer, Map<String, Object> state) {
        
        log.info("make_choice");
        GameState gameState = new GameState(state);
        log.fine(gameState.toString());
        Bot bot = new Bot4(gameState);
        if(gameState.opponent_id == 17) {
            bot = new Bot5(gameState);
        }
        
        return bot.choose(offer);
    }

    public void move_result(Map<String, Object> result) {
        log.info("move_result");
        //MoveResult moveResult = new MoveResult(result);
        //log.info(moveResult.toString());
    }

    public void game_result(Map<String, Object> result) {
        log.info("game_result");
        GameResult gameResult = new GameResult(result);
        log.info(gameResult.toString());
        Result.isWin(gameResult.toString());
    }

    public static void main(String[] args) throws Exception {
        Snack.setDefault();
        
        int port = 9999;
        WebServer webServer = new WebServer(port);
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        phm.setVoidMethodEnabled(true);

        // class key = null for no prefix on method names
        phm.addHandler(null, ContestServer.class);
        xmlRpcServer.setHandlerMapping(phm);

        // allow null values
        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(true);

        log.setLevel(Level.FINE);
        log.info("starting XML-RPC server at /RPC2 on port " + port);
        Result.main(args);
        webServer.start();
        
        
    }
}
