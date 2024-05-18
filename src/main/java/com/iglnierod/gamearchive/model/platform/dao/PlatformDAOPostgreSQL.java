/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.platform.dao;

import com.iglnierod.gamearchive.model.database.Database;
import com.iglnierod.gamearchive.model.platform.Platform;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author rodri
 */
public class PlatformDAOPostgreSQL implements PlatformDAO {

    private Database database;

    public PlatformDAOPostgreSQL(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Platform> getAll() {
        String query = "SELECT * FROM platform";
        ArrayList<Platform> platformsList = new ArrayList<>();
        try (Statement stmt = database.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Platform platform = new Platform(
                        rs.getInt("id"),
                        rs.getString("checksum"),
                        rs.getString("abbreviation"),
                        rs.getString("name"),
                        rs.getBoolean("default")
                );

                platformsList.add(platform);
            }
            return platformsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Platform get(int id) {
        // TODO
        String query = "SELECT * FROM platform WHERE id = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            //int id, String checksum, String abbreviation, String logoID, String name
            Platform platform = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                platform = new Platform(
                        rs.getInt("id"),
                        rs.getString("checksum"),
                        rs.getString("abbreviation"),
                        rs.getString("name"),
                        rs.getBoolean("default")
                );
            }
            return platform;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Platform getByAbbreviation(String abbreviation) {
        // TODO
        String query = "SELECT * FROM platform WHERE abbreviation = ?";
        Platform platform = null;
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, abbreviation);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                platform = new Platform(
                        rs.getInt("id"),
                        rs.getString("checksum"),
                        rs.getString("abbreviation"),
                        rs.getString("name"),
                        rs.getBoolean("default")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return platform;
    }

    @Override
    public Set<Platform> getPlatformsByAbbreviation(Set<String> platforms) {
        Set<Platform> platformsSet = new LinkedHashSet<>();
        for (String platformAbbreviation : platforms) {
            Platform platform = this.getByAbbreviation(platformAbbreviation);
            if (platform != null) {
                platformsSet.add(platform);
            }
        }
        return platformsSet;
    }

    @Override
    public ArrayList<Integer> getPlatformsById(String platformsArray) {
        ArrayList<Integer> result = new ArrayList<>();

        // Eliminar los caracteres "{" y "}" y dividir la cadena por comas
        String[] parts = platformsArray.replaceAll("[{}]", "").split(",");

        // Convertir los elementos a enteros y agregarlos al ArrayList
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part.trim());
                result.add(num);
            } catch (NumberFormatException e) {
                // Manejar la excepción si no se puede convertir a entero
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public Set<Platform> getPlatformsByUser(String username) {
        Set<Platform> userPlatforms = new LinkedHashSet<>();
        String query = "SELECT * FROM platform WHERE id IN (SELECT platform_id FROM client_platform WHERE username = ?)";

        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Platform p = new Platform();
                p.setId(rs.getInt("id"));
                p.setAbbreviation(rs.getString("abbreviation"));
                p.setChecksum(rs.getString("checksum"));
                p.setName(rs.getString("name"));
                p.setDefaultConfig(rs.getBoolean("default"));
                userPlatforms.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userPlatforms;
    }

    // Save platforms information in DB
    @Override
    public void savePlatforms(ArrayList<Platform> platforms) {
        System.out.println("platformDao: " + platforms);
        for (Platform p : platforms) {
            savePlatform(p);
        }
    }

    @Override
    public void savePlatform(Platform platform) {
        String query = "INSERT INTO platform VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, platform.getId());
            ps.setString(2, platform.getChecksum());
            ps.setString(3, platform.getAbbreviation());
            ps.setString(4, platform.getName());
            ps.setBoolean(5, platform.isDefaultConfig());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("PLATFORM ALREADY IN BD");
        }
    }

    @Override
    public void buildRelation(int gameId, ArrayList<Platform> platforms) {
        String query = "INSERT INTO platform_game (platform_id, game_id) VALUES (?,?)";

        try (Connection connection = database.getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {
            for (Platform platform : platforms) {
                ps.setInt(1, platform.getId());
                ps.setInt(2, gameId);
                ps.addBatch(); // Agrega la consulta al lote de ejecución
            }
            ps.executeBatch(); // Ejecuta todas las consultas en el lote de una vez
        } catch (SQLException ex) {
            System.err.println("ERROR BUILDING RELATION PLATFORM->GAME: " + ex.getMessage());
        }
    }

    @Override
    public boolean platformExists(Platform platform) {
        String query = "SELECT id FROM platform WHERE id = ?";
        try (PreparedStatement ps = database.getConnection().prepareStatement(query)) {
            ps.setInt(1, platform.getId());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Set<Integer> getExistingPlatformIds(ArrayList<Platform> platforms, Connection connection) throws SQLException {
        Set<Integer> existingPlatformIds = new HashSet<>();
        String query = "SELECT id FROM platform WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            for (Platform platform : platforms) {
                ps.setInt(1, platform.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        existingPlatformIds.add(platform.getId());
                    }
                }
            }
        }
        return existingPlatformIds;
    }

    @Override
    public void savePlatformsInBatch(ArrayList<Platform> platforms, Set<Integer> existingPlatformIds, Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO platform (id, name) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
            for (Platform platform : platforms) {
                if (!existingPlatformIds.contains(platform.getId())) {
                    ps.setInt(1, platform.getId());
                    ps.setString(2, platform.getName());
                    ps.addBatch();
                }
            }
            ps.executeBatch();
        }
    }

}
