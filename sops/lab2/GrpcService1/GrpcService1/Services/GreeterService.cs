using Google.Protobuf.Collections;
using Grpc.Core;
using GrpcService1;
using GrpcService1.Models;
using Microsoft.EntityFrameworkCore.Query.Internal;
using Microsoft.IdentityModel.Tokens;

namespace GrpcService1.Services;

public class GreeterService : Greeter.GreeterBase 
{
    CrittersContext crittersContext = new CrittersContext();
    
    public override Task<MessageResponse> GetServerResponse(Message request, ServerCallContext context)
    {
        var resp = new RepeatedField<Player_>();

        if (request.EndDate.Equals("-9999") && request.Position.Equals("-9999") && request.FromDate.Equals("-9999") && request.Datasource.Equals("roaster"))
        {
            var list = ReturnAllPlayers();
            resp = AssignToRepeated(list);
        }
        else if (request.Datasource.Equals("roaster"))
        {
                crittersContext.ClearTemp();

                DateTime start = new DateTime();
                DateTime end = new DateTime();

                if (request.FromDate.IsNullOrEmpty())
                {
                    start = DateTime.MinValue;
                }
                else
                {
                    start = DateTime.Parse(request.FromDate);
                }

                if (request.EndDate.IsNullOrEmpty())
                {
                    end = DateTime.MaxValue;
                }
                else
                {
                    end = DateTime.Parse(request.EndDate);
                }

                foreach (var rosterPlayer in SelectPlayers(request.Position, start, end))
                {
                    crittersContext.MoveToTemp(rosterPlayer);
                }

                resp = AssignToRepeated(ReturnAllTemp());
        }

        if (request.Datasource.Equals("temp"))
        {
            resp = AssignToRepeated(ReturnAllTemp());
        }

        if (request.Position.Equals("-8888") && request.Datasource.Equals("temp"))
        {
            if (request.FromDate.Equals("null"))
            {
                crittersContext.MoveAllToRoster();
            }
            else
            {
                crittersContext.MoveToRoster(crittersContext.temp.Single(e => e.playerid == request.FromDate));
            }
        }

        if (request.EndDate.Equals("save"))
        {
            var playerid = request.Datasource;
            foreach (var item in FindPlayer(playerid))
            {
                if (request.FromDate.Equals(""))
                {
                    request.FromDate = item.birthday.ToString();
                }
                crittersContext.SaveCritter(item, playerid, Convert.ToInt32(item.jersey), item.fname, item.sname, item.position, request.FromDate,
                    Convert.ToInt32(item.weight), Convert.ToInt32(item.height), request.Position, item.birthstate);
            }
        }

        return Task.FromResult(new MessageResponse
        {
            Players = { resp }
        });
    }

    private RepeatedField<Player_> AssignToRepeated(IEnumerable<Player> list)
    {
        var ans = new RepeatedField<Player_>();

        foreach (var pl in list)
        {
            var player = new Player_();
            player.Id = pl.playerid;
            player.Birthcity = pl.birthcity;
            player.Birthday = pl.birthday.ToString();
            player.Birthstate = pl.birthstate;
            player.Fname = pl.fname;
            player.Sname = pl.sname;
            player.Jersey = pl.jersey;
            player.Height = pl.height;
            player.Weight = pl.weight;
            player.Position = pl.position;
            ans.Add(player);
        }

        return ans;
    }
    
    private IEnumerable<Player> SelectPlayers(string position, DateTime? startDate, DateTime? endDate)
    {
        if (!startDate.HasValue)
        {
            startDate = DateTime.MinValue;
        }
        if (!endDate.HasValue)
        {
            endDate = DateTime.MaxValue;
        }

        if (position == "null" || position.Length == 0)
        {
            return crittersContext.roster.Where(e => startDate <= e.birthday && endDate >= e.birthday);
        } 
        else
        {
            return crittersContext.roster.Where(
                predicate: e => e.position == position && startDate <= e.birthday && endDate >= e.birthday
            );
        }
    }
    
    private IEnumerable<Player> FindPlayer(string playerid)
    {
        if (playerid != null || playerid.Length != 0)
        {
            return crittersContext.roster.Where(predicate: e => e.playerid == playerid);
        }
        else
        {
            return crittersContext.roster.Where(e => e.playerid == playerid);
        }
    }

    public IEnumerable<Player> ReturnAllPlayers()
    { 
        return crittersContext.roster.ToList();
    }
    
    public IEnumerable<Player> ReturnAllTemp()
    {
        return crittersContext.temp.ToList();
    }
}
